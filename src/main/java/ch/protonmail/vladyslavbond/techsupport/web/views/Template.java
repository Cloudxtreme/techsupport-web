package ch.protonmail.vladyslavbond.techsupport.web.views;

public interface Template
{
    public abstract Template bind (String key, String value);

    public abstract Template bind (String key, java.util.List<String> values);

    public abstract String build ( )
    throws TemplateParameterMissing, TemplateValueMissing, TemplateFileMissing;
}