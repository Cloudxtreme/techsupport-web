package ch.protonmail.vladyslavbond.techsupport.web.views;

import java.util.ArrayList;
import java.util.List;

public final class FormFieldsetView
extends AbstractView
implements View
{
    private final String id;
    private final String legendTextNode;

    private final List<String> labelsHTML = new ArrayList<String> ( );

    public FormFieldsetView (String id, String legend)
    throws ViewException
    {
        this.id = new String(id);
        this.legendTextNode = new String(legend);
    }

    public FormFieldsetView addLabel (FormLabelView labelView)
    throws ViewException
    {
        this.labelsHTML.add(labelView.getHTML( ));
        return this;
    }

    @Override
    public String getHTML ( )
    throws ViewException
    {
        try
        {
            return NativeTemplate.getInstance("fieldset.html")
                .bind("id", this.id)
                .bind("legendTextNode", this.legendTextNode)
                .bind("labels", this.labelsHTML)
                .build( );
        } catch (TemplateValueMissing | TemplateParameterMissing | TemplateFileMissing e) {
            throw new ViewException (e);
        }
    }
}