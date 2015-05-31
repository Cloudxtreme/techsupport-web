package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Map;
import java.util.HashMap;

class NativeTemplate implements Template
{
    private static final Pattern pattern = Pattern.compile("\\$\\{(?<parameter>.*?)\\}"); 
    private final Map<String, String> mapping = new HashMap<String, String> ( );
    private final String template;

    public NativeTemplate (String template)
    {
        this.template = template;
    }

    @Override
    public Template bind (String key, String value)
    {
        this.mapping.put(key, value);
        return this;
    }

    @Override
    public String build ( )
    throws TemplateParameterMissing
    {
        try 
        (
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass( ).getClassLoader( ).getResourceAsStream(this.template)));
        )
        {
            StringBuffer stringBuffer = new StringBuffer( );
            String pieceOfTemplate;
            while ((pieceOfTemplate = reader.readLine( )) != null)
            {
                Matcher matcher;
                matcher = NativeTemplate.pattern.matcher(pieceOfTemplate);
                while (matcher.find( ))
                {
                    String key = matcher.group("parameter");
                    String value = this.mapping.get(key);
                    if (value == null || value.isEmpty( ))
                    {
                        throw new TemplateParameterMissing (key, this);
                    }
                    matcher.appendReplacement(stringBuffer, value);
                }
                matcher.appendTail(stringBuffer);
            }
            return stringBuffer.toString( );
        } catch (IOException e) {
            throw new AssertionError ("Failed to read template.", e);
        }
    }
}