package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;

public interface IssueController
extends Controller
{
    public abstract Issue assign (Party assigningParty, Party assignedParty, Issue issue) throws IssueControllerException;

    public abstract Issue close (Party closingParty, Issue issue) throws IssueControllerException;

    public abstract Issue open (Party issuingParty, String description) throws IssueControllerException;
}