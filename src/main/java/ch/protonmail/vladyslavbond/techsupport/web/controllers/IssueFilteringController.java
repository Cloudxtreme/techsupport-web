package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactoryException;

import java.util.List;

public interface IssueFilteringController
extends Controller
{
    public abstract List<Issue> filter ( ) throws IssueFactoryException;
}