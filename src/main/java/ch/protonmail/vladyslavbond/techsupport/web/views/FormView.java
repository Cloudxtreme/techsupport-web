package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.util.ArrayList;
import java.util.List;

public final class FormView
extends AbstractView
implements View
{
    private List<String> fieldsetsHTML = new ArrayList<String> ( );

    private final String id;
    private final String name;
    private final String action;
    private final String method;

    public FormView (String id, String name, String formAction, FormMethod formMethod)
    {
        this.id = new String (id);
        this.name = new String (name);
        this.action = new String(formAction);
        this.method = formMethod.toString( );
    }

    public FormView addFieldset (FormFieldsetView fieldsetView) throws ViewException
    {
        this.fieldsetsHTML.add(fieldsetView.getHTML( ));
        return this;
    }
    
    @Override
    public String getHTML ( ) throws ViewException
    {
        try
        {
            return NativeTemplate.getInstance("form.html")
                .bind("id", this.id)
                .bind("name", this.name)
                .bind("method", this.method)
                .bind("action", this.action)
                .bind("fieldsets", this.fieldsetsHTML)
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }
}
