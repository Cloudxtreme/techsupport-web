package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class TemplateValueMissing extends Exception
{
    private final String   key;
    private final Template template;

    public TemplateValueMissing (Template template, String key)
    {
        this.key      = key;
        this.template = template;
    }

    @Override
    public String getMessage ( )
    {
        return "No value was binded to parameter " + this.key + " in template " + this.template.getClass( ).toString( ) + ".";
    }
}