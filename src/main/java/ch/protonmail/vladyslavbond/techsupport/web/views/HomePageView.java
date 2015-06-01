package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import java.util.Set;

public final class HomePageView
extends Object
implements View
{
    private final String html;

    public HomePageView (Set<Party> setOfParties) throws ViewException
    {
        try
        {
            FormIssueOpenView formView = new FormIssueOpenView (new FormSelectView ("party_id", "party_id", setOfParties));
            Template indexTemplate = NativeTemplate.getInstance("index.html");
            this.html = indexTemplate
                .bind("form", formView.getHTML( ))
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }
    
    @Override
    public String getHTML ( )
    {
        return new String(this.html);
    }
}
