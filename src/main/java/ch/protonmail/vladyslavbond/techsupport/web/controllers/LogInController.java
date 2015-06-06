package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Factories;
import ch.protonmail.vladyslavbond.techsupport.domain.PartyFactoryException;
import ch.protonmail.vladyslavbond.techsupport.domain.Party;

public final class LogInController
{
    public static Party logIn (String username, String password)
    throws WrongPasswordException, UnknownPartyException
    {
        try
        {
            return Factories.getPartyFactory( ).getInstance(username, password);
        } catch (PartyFactoryException e) {
            throw new UnknownPartyException (username);
        }
    }
}