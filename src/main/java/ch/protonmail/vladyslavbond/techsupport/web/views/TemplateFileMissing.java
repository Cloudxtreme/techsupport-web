package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class TemplateFileMissing extends Exception
{
    private final String pathToTemplate;

    public TemplateFileMissing (String pathToTemplate)
    {
        this.pathToTemplate = new String(pathToTemplate);
    }

    @Override
    public String getMessage ( )
    {
        return "Failed to locate file of the template by the following path \"" + this.pathToTemplate + "\".";
    }
}