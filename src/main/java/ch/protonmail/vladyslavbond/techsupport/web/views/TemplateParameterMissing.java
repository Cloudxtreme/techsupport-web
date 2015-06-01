package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class TemplateParameterMissing extends Exception
{
    private final String   nameOfTheParameter;
    private final Template template;

    public TemplateParameterMissing (String nameOfTheParameter, Template template)
    {
        this.nameOfTheParameter = nameOfTheParameter;
        this.template           = template;
    }

    @Override
    public String getMessage ( )
    {
        return "Parameter with name \"" + this.nameOfTheParameter + "\" is missing in the template " + this.template.toString( ) + ".";
    }
}