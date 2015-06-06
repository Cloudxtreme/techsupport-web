package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.util.ArrayList;
import java.util.List;

public final class PageView
extends AbstractView
implements View
{
    private final String html;

    public PageView (String titleOfPage, List<String> contentOfBody) throws ViewException
    {
        try
        {
            this.html = NativeTemplate.getInstance("page_standard.html")
                .bind("titleOfPage", titleOfPage)
                .bind("contentOfBody", contentOfBody)
                .build( );
        } catch (TemplateParameterMissing | TemplateValueMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }

    public PageView (String titleOfPage, String contentOfBody) throws ViewException
    {
        try
        {
            List<String> list = new ArrayList<String> ( );
            list.add(contentOfBody);
            this.html = NativeTemplate.getInstance("page_standard.html")
                .bind("titleOfPage", titleOfPage)
                .bind("contentOfBody", list)
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