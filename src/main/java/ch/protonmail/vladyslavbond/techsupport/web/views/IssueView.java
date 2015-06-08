package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueAssigned;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueClosed;
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
            Template template = NativeTemplate.getInstance("issue.html")
                .bind("idOfIssue", issue.getId( ).toString( ))
                .bind("descriptionOfIssue", issue.getDescription( ).getText( ))
                .bind("actionOfFormNamedDiscuss", Page.DISCUSS.getPath( ))
            ;
            if (((IssueClosed)issue).getDateClosed( ) != null)
            {
                template
                .bind("statusOfIssue", IssueStatus.CLOSED.toString( ))
                .bind("footerTextNode", "Closed by " + ((IssueClosed)issue).getClosing( ).getName( ) + " at " + ((IssueClosed)issue).getDateClosed( ).toString( ) + ".")
                ;
            } else if (((IssueAssigned)issue).getDateAssigned( ) != null) {
                template
                .bind("statusOfIssue", IssueStatus.ASSIGNED.toString( ))
                .bind("footerTextNode", "Assigned to " + ((IssueAssigned)issue).getAssigned( ).getName( ) + " by " + ((IssueAssigned)issue).getAssignee( ).getName( ) + " at " + ((IssueAssigned)issue).getDateAssigned( ).toString( ) + ".")
                ;
            } else {
                template
                .bind("statusOfIssue", IssueStatus.OPEN.toString( ))
                .bind("footerTextNode", "Issued by " + issue.getIssuing( ).getName( ) + " at " + issue.getDateIssued( ).toString( ) + ".")
                ;
            }
            this.html = template.build( );
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