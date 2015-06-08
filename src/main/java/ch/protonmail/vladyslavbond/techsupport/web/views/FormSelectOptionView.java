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
        this(optionValue, optionTextNode, false);
    }

    public FormSelectOptionView (String optionValue, String optionTextNode, boolean isSelected)
    throws ViewException
    {
        try
        {
            if (isSelected == true)
            {
                Template optionTemplate = NativeTemplate.getInstance("option.html");
                this.html = optionTemplate
                    .bind("value", optionValue)
                    .bind("textNode", optionTextNode)
                    .bind("selected", "selected")
                    .build( );
            } else {
                Template optionTemplate = NativeTemplate.getInstance("option.html");
                this.html = optionTemplate
                    .bind("value", optionValue)
                    .bind("textNode", optionTextNode)
                    .bind("selected", "")
                    .build( );
            }
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }

    public FormSelectOptionView (Party party) throws ViewException
    {
        this(party.getId( ).toString( ), party.getName( ));
    }

    public FormSelectOptionView (Party party, boolean isSelected) throws ViewException
    {
        this(party.getId( ).toString( ), party.getName( ), isSelected);
    }
    
    @Override
    public String getHTML ( )
    throws ViewException
    {
        return new String(this.html);
    }
}
