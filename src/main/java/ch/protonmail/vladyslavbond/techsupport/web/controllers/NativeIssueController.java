package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Factories;
import ch.protonmail.vladyslavbond.techsupport.domain.Identificator;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueAssignable;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueClosable;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactoryException;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyFactoryException;

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
            throw new IssueControllerException (e);
        }
        return issue;
    }

    @Override
    public Issue assign (Party assigningParty, Party assignedParty, Identificator<Issue> idOfIssue) 
    throws IssueControllerException
    {
        try
        {
            return this.assign(assigningParty, assignedParty, Factories.getIssueFactory( ).getInstance(assigningParty, idOfIssue));
        } catch (IssueFactoryException e) {
            throw new IssueControllerException (e);
        }
    }

    @Override
    public Issue assign (Party assigningParty, Identificator<Party> idOfAssignedParty, Identificator<Issue> idOfIssue)
    throws IssueControllerException
    {
        try
        {
            return this.assign(assigningParty, Factories.getPartyFactory( ).getInstance(assigningParty, idOfAssignedParty), idOfIssue);
        } catch (PartyFactoryException e) {
            throw new IssueControllerException (e);
        }
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
            throw new IssueControllerException (e);
        }
        return issue;
    }

    @Override
    public Issue close (Party closingParty, Identificator<Issue> idOfIssue) 
    throws IssueControllerException
    {
        try
        {
            return this.close(closingParty, Factories.getIssueFactory( ).getInstance(closingParty, idOfIssue));
        } catch (IssueFactoryException e) {
            throw new IssueControllerException (e);
        }
    }

    @Override
    public Issue discuss (Party readingParty, Identificator<Issue> idOfIssue) 
    throws IssueControllerException
    {
        try
        {
            return Factories.getIssueFactory( ).getInstance(readingParty, idOfIssue);
        } catch (IssueFactoryException e) {
            throw new IssueControllerException (e);
        }        
    }

    @Override
    public Issue open (Party issuingParty, String description)
    throws IssueControllerException
    {
        try
        {
            return Factories.getIssueFactory( ).getInstance(issuingParty, description);
        } catch (IssueFactoryException e) {
            throw new IssueControllerException (e);
        }
    }
}