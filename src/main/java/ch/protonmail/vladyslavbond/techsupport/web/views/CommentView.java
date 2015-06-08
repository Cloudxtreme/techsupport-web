package ch.protonmail.vladyslavbond.techsupport.web.views;

import ch.protonmail.vladyslavbond.techsupport.domain.Comment;

public final class CommentView
extends AbstractView
implements View
{
    private final Comment comment;

    public CommentView (Comment comment)
    {
        this.comment = comment;
    }
    
    @Override
    public String getHTML ( )
    throws ViewException
    {
        return String
            .format("<article id='%2$d' class='comment'><header><h2>Comment #%2$d on issue #%1$d.</h2></header><section>%3$s</section><footer>Posted at %5$tT by %4$s.</footer></article>"
                    , this.comment.getIssue( ).getId( ).intValue( )
                    , this.comment.getId( ).intValue( )
                    , this.comment.getText( )
                    , this.comment.getAuthor( ).getName( )
                    , this.comment.getDate( )
                );
    }
}