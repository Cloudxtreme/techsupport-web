package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

final class NativeTemplate 
extends Object
implements Template
{
    private static final Pattern pattern = Pattern.compile("\\$\\{(?<parameter>.*?)\\}"); 
    private static final Map<String, Template> resourceNameToTemplateMapping = new HashMap<String, Template> ( );

    public static Template getInstance (String pathToTemplate)
    {
        if (NativeTemplate.resourceNameToTemplateMapping.containsKey(pathToTemplate))
        {
            return NativeTemplate.resourceNameToTemplateMapping.get(pathToTemplate);
        }
        Template newTemplate = new NativeTemplate (pathToTemplate);
        NativeTemplate.resourceNameToTemplateMapping.put(pathToTemplate, newTemplate);
        return newTemplate;
    }

    private final Map<String, List<String>> mapping = new HashMap<String, List<String>> ( );
    private final String pathToTemplate;

    private NativeTemplate (String pathToTemplate)
    {
        this.pathToTemplate = new String(pathToTemplate);
    }

    @Override
    public Template bind (String key, String value)
    {
        List<String> values = new ArrayList<String> ( );
        values.add(value);
        return this.bind(key, values);
    }

    @Override
    public Template bind (String key, List<String> values)
    {
        this.mapping.put(key, values);
        return this;
    }

    @Override
    public String build ( )
    throws TemplateParameterMissing, TemplateValueMissing, TemplateFileMissing
    {
        try 
        (
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass( ).getClassLoader( ).getResourceAsStream(this.pathToTemplate)));
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
                    if (!this.mapping.containsKey(key))
                    {
                        throw new TemplateValueMissing (this, key);
                    }
                    String values = "";
                    for (String value : this.mapping.get(key))
                    {
                        values += value;
                    }
                    matcher.appendReplacement(stringBuffer, values);
                }
                matcher.appendTail(stringBuffer);
            }
            this.mapping.clear( );
            return stringBuffer.toString( );
        } catch (FileNotFoundException e) {
            throw new TemplateFileMissing (this.pathToTemplate);
        } catch (IOException e) {
            throw new RuntimeException ("Failed to build a template.", e);
        }
    }
}