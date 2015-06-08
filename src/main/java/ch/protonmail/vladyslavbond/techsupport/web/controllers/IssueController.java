package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.Identificator;

public interface IssueController
extends Controller
{
    public abstract Issue assign (Party assigningParty, Party assignedParty, Issue issue) throws IssueControllerException;

    public abstract Issue assign (Party assigningParty, Party assignedParty, Identificator<Issue> idOfIssue) throws IssueControllerException;

    public abstract Issue assign (Party assigningParty, Identificator<Party> idOfAssignedParty, Identificator<Issue> idOfIssue) throws IssueControllerException;

    public abstract Issue close (Party closingParty, Issue issue) throws IssueControllerException;

    public abstract Issue close (Party closingParty, Identificator<Issue> idOfIssue) throws IssueControllerException;

    public abstract Issue discuss (Party readingParty, Identificator<Issue> idOfIssue) throws IssueControllerException;

    public abstract Issue open (Party issuingParty, String description) throws IssueControllerException;
}