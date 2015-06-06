package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;
import ch.protonmail.vladyslavbond.techsupport.domain.Issue;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueStatus;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactory;
import ch.protonmail.vladyslavbond.techsupport.domain.IssueFactoryException;
import ch.protonmail.vladyslavbond.techsupport.domain.Factories;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

final class NativeIssueFilteringController
extends Object
implements IssueFilteringController
{
    public static IssueFilteringController newInstance (Party readingParty, IssueFilteringMode modeSelected)
    {
        return new NativeIssueFilteringController (readingParty, modeSelected);
    }

    public static IssueFilteringController newInstance (Party readingParty)
    {
        return new NativeIssueFilteringController (readingParty, IssueFilteringMode.ALL);
    }

    /*
     * Mode independent fields.
     */
    private final IssueFilteringMode modeSelected;
    private       Party              readingParty;
    private       Date               dateBorderLower;
    private       Date               dateBorderUpper;
    private       String             stringToFindInContentOfIssue;

    /*
     * Mode dependent fields.
     */
    private       Party partyAssignedTo;
    private       Party partyAssignedBy;
    private       Party partyClosedBy;
    private       Party partyIssuedBy;

    private NativeIssueFilteringController (Party readingParty, IssueFilteringMode modeSelected)
    {
        this.readingParty = readingParty;
        this.modeSelected = modeSelected;
    }

    public IssueFilteringController setRange (Date dateBorderLower, Date dateBorderUpper)
    {
        if (dateBorderLower != null && dateBorderUpper != null)
        {
            if (dateBorderLower.after(dateBorderUpper) || dateBorderUpper.before(dateBorderLower))
            {
                this.dateBorderLower = dateBorderUpper;
                this.dateBorderUpper = dateBorderLower;
                return this;
            }
            this.dateBorderLower = dateBorderLower;
            this.dateBorderUpper = dateBorderUpper;
        } else if (dateBorderLower != null) {
            //this.after(dateBorderLower);
        } else if (dateBorderUpper != null) {
            //this.until(dateBorderUpper);
        }
        return this;
    }

    public IssueFilteringController contains (String stringToFindInContentOfIssue) 
    {
        this.stringToFindInContentOfIssue = stringToFindInContentOfIssue;
        return this;
    }

    /*
     * TODO: implement the rest of possible filtering options.
     */
    @Override
    public List<Issue> filter ( ) throws IssueFactoryException
    {
        IssueFactory issueFactory = Factories.getIssueFactory( );
        switch (this.modeSelected)
        {
            case ALL:
                return issueFactory.getInstance(this.readingParty, IssueStatus.OPEN);

            case ASSIGNED:
                return issueFactory.getInstance(this.readingParty, IssueStatus.ASSIGNED);

            case CLOSED:
                return issueFactory.getInstance(this.readingParty, IssueStatus.CLOSED);

            case OPEN:
                return issueFactory.getInstance(this.readingParty, IssueStatus.OPEN);

            default:
                throw new AssertionError ("Unknown filtering mode " + this.modeSelected.toString( ) + ".");
        }
    }
}