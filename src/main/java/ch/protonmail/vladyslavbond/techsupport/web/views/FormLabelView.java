package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class FormLabelView
extends AbstractView
implements View
{
    private final String html;

    public FormLabelView (String labelTextNode, FormInputView formInputView)
    throws ViewException
    {
        try
        {
            this.html = NativeTemplate.getInstance("label.html")
                .bind("labelTextNode", labelTextNode)
                .bind("input", formInputView.getHTML( ))
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