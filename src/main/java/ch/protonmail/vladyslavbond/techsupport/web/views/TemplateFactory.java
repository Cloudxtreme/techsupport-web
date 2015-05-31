package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.io.IOException;
import java.io.InputStreamReader;

public final class TemplateFactory
{
    public static Template newInstance (String nameOfTemplate) throws IOException
    {
        return new NativeTemplate (nameOfTemplate + ".html");
    }
}