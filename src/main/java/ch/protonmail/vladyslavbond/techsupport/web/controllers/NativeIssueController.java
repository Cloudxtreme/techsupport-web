package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.Factories;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueAssignable;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueClosable;

enum NativeIssueController
implements IssueController
{
    INSTANCE ( );

    private NativeIssueController ( )
    {

    }

    @Override
    public Issue assign (Party assigningParty, Party assignedParty, Issue issue)
    throws IssueControllerException
    {
        try
        {
            IssueAssignable assignable = (IssueAssignable)issue;
            assignable.assign(assigningParty, assignedParty);
        } catch (RuntimeException e) {
            throw new IssueControllerException ("Issue controller failed to process a request.", e);
        }
        return issue;
    }

    @Override
    public Issue close (Party closingParty, Issue issue)
    throws IssueControllerException
    {
        try
        {
            IssueClosable closable = (IssueClosable)issue;
            closable.close(closingParty);
        } catch (RuntimeException e) {
            throw new IssueControllerException ("Issue controller failed to process a request.", e);
        }
        return issue;
    }

    @Override
    public Issue open (Party issuingParty, String description)
    throws IssueControllerException
    {
        try
        {
            return Factories.getIssueFactory( ).getInstance(issuingParty, description);
        } catch (Exception e) {
            throw new IssueControllerException ("Issue controller failed to process a request.", e);
        }
    }
}