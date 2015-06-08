package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import java.util.Set;

public final class FormIssueOpenView
extends AbstractView
implements View
{
    private final String html;

    public FormIssueOpenView (FormSelectView selectParties) throws ViewException 
    {
        try
        {
            Template formTemplate = NativeTemplate.getInstance("form_issue_open.html");
            this.html = formTemplate
                .bind("action", Page.ISSUE_OPEN.getPath( ))
                .bind("selectParties", selectParties.getHTML( ))
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
