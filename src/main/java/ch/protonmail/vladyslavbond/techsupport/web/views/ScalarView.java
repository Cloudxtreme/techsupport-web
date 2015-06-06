package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class ScalarView
extends AbstractView
implements View
{
    private final String html;

    public ScalarView ( )
    {
        this.html = "<p>Some random text here.</p>";
    }

    public ScalarView (String html)
    {
        this.html = new String(html);
    }

    public ScalarView (Integer i)
    {
        this.html = i.toString( );
    }

    public ScalarView (Float f)
    {
        this.html = f.toString( );
    }
    
    @Override
    public String getHTML ( )
    throws ViewException
    {
        return new String(this.html);
    }
}