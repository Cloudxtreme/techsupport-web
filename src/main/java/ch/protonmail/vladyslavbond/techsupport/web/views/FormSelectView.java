package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import java.util.Collection;

public final class FormSelectView
extends AbstractView
implements View
{
    private final String html;

    public FormSelectView (String selectId, String selectName, String options)
    throws ViewException
    {
        try
        {
            Template selectTemplate = NativeTemplate.getInstance("select.html");
            this.html = selectTemplate
                .bind("id", selectId)
                .bind("name", selectName)
                .bind("options", options)
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }

    public FormSelectView (String selectId, String selectName, Collection<Party> parties)
    throws ViewException
    {
        try
        {
            String options = "";
            for (Party party : parties)
            {
                FormSelectOptionView optionView = new FormSelectOptionView (party);
                options += optionView.getHTML( );
            }
            Template selectTemplate = NativeTemplate.getInstance("select.html");
            this.html = selectTemplate
                .bind("id", selectId)
                .bind("name", selectName)
                .bind("options", options)
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
