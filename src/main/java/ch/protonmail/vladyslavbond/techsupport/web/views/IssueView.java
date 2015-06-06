package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueStatus;

public final class IssueView
extends AbstractView
implements View
{
    private final String html;

    public IssueView (Issue issue) throws ViewException
    {
        try
        {
            this.html = NativeTemplate.getInstance("issue.html")
                .bind("idOfIssue", issue.getId( ).toString( ))
                .bind("descriptionOfIssue", issue.getDescription( ).getText( ))
                .bind("actionOfFormNamedDiscuss", Page.DISCUSS.getPath( ))
                .bind("statusOfIssue", IssueStatus.OPEN.toString( ))
                .bind("nameOfPartyIssuedBy", issue.getIssuing( ).getName( ))
                .bind("dateIssueIssuedAt", issue.getDateIssued( ).toString( ))
                .build( );
        } catch (TemplateParameterMissing | TemplateValueMissing | TemplateFileMissing e) {
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