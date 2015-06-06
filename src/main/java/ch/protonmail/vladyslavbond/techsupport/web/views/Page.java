package ch.protonmail.vladyslavbond.techsupport.web.views;

public enum Page
{
    LOG_IN ("login"), ERROR ("error"), HOME (""), DISCUSS ("discuss");

    private final String path;

    private Page (String path)
    {
        this.path = path;
    }

    public String getPath ( )
    {
        return this.path;
    }
}