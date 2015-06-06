package ch.protonmail.vladyslavbond.techsupport.web.views;

public final class LogInPageView
extends AbstractView
implements View
{
    private final PageView pageView;

    public LogInPageView ( ) throws ViewException
    {
        FormView logInFormView = new FormView ("", "", Page.LOG_IN.getPath( ), FormMethod.POST);
        FormFieldsetView logInFormFieldset = new FormFieldsetView ("", "Log in to the system.");
        FormInputView inputUsername = new FormInputView (FormInputType.TEXT, "username", "username", "My username.");
        FormInputView inputPassword = new FormInputView (FormInputType.PASSWORD, "password", "password", "My password.");
        FormInputView inputSubmit = new FormInputView (FormInputType.SUBMIT, "submit", "submit", "Submit", "");
        FormLabelView labelUsername = new FormLabelView ("Username:", inputUsername);
        FormLabelView labelPassword = new FormLabelView ("Password:", inputPassword);
        FormLabelView labelSubmit = new FormLabelView ("", inputSubmit);
        logInFormFieldset.addLabel(labelUsername);
        logInFormFieldset.addLabel(labelPassword);
        logInFormFieldset.addLabel(labelSubmit);
        logInFormView.addFieldset(logInFormFieldset);
        this.pageView = new PageView ("Log in page.", logInFormView.getHTML( ));
    }

    @Override
    public String getHTML ( )
    throws ViewException
    {
        return this.pageView.getHTML( );
    }
}