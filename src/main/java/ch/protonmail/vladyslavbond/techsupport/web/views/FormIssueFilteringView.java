package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.web.controllers.IssueFilteringMode;

public final class FormIssueFilteringView
extends AbstractView
implements View
{
    private final String html;

    public FormIssueFilteringView (IssueFilteringMode modeSelected)
    throws ViewException
    {
        try
        {
            switch (modeSelected)
            {
                case ASSIGNED:
                    this.html = NativeTemplate.getInstance("form_filter_issues.html")
                        .bind("selectedAll", "")
                        .bind("selectedAssigned", "selected")
                        .bind("selectedClosed", "")
                        .bind("selectedOpen", "")
                        .build( )
                    ;
                    break;

                case CLOSED:
                    this.html = NativeTemplate.getInstance("form_filter_issues.html")
                        .bind("selectedAll", "")
                        .bind("selectedAssigned", "")
                        .bind("selectedClosed", "selected")
                        .bind("selectedOpen", "")
                        .build( )
                    ;
                    break;

                case OPEN:
                    this.html = NativeTemplate.getInstance("form_filter_issues.html")
                        .bind("selectedAll", "")
                        .bind("selectedAssigned", "")
                        .bind("selectedClosed", "")
                        .bind("selectedOpen", "selected")
                        .build( )
                    ;
                    break;

                default:
                    this.html = NativeTemplate.getInstance("form_filter_issues.html")
                        .bind("selectedAll", "selected")
                        .bind("selectedAssigned", "")
                        .bind("selectedClosed", "")
                        .bind("selectedOpen", "")
                        .build( )
                    ;
            }
        } catch (Exception e) {
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