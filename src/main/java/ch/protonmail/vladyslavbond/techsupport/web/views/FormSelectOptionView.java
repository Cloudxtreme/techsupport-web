package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;

public final class FormSelectOptionView
extends AbstractView
implements View
{
    private final String html;

    public FormSelectOptionView (String optionValue, String optionTextNode)
    throws ViewException
    {
        try
        {
            Template optionTemplate = NativeTemplate.getInstance("option.html");
            this.html = optionTemplate
                .bind("value", optionValue)
                .bind("textNode", optionTextNode)
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }

    public FormSelectOptionView (Party party) throws ViewException
    {
        this(party.getId( ).toString( ), party.getName( ));
    }
    
    @Override
    public String getHTML ( )
    throws ViewException
    {
        return new String(this.html);
    }
}
