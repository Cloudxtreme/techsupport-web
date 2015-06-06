package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class FormInputView
extends AbstractView
implements View
{
    private final String html;

    public FormInputView (FormInputType formInputType, String id, String name, String value, String placeholder)
    throws ViewException
    {
        try
        {
            this.html = NativeTemplate.getInstance("input.html")
                .bind("type", formInputType.toString( ))
                .bind("id", id)
                .bind("name", name)
                .bind("value", value)
                .bind("placeholder", placeholder)
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }

    public FormInputView (FormInputType formInputType, String id, String name, String placeholder)
    throws ViewException
    {
        try
        {
            this.html = NativeTemplate.getInstance("input_text.html")
                .bind("type", formInputType.toString( ))
                .bind("id", id)
                .bind("name", name)
                .bind("placeholder", placeholder)
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }

    @Override
    public String getHTML ( )
    throws ViewException
    {
        return new String(this.html);
    }
}