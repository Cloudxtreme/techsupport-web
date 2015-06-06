package ch.protonmail.vladyslavbond.techsupport.web.controllers;

import ch.protonmail.vladyslavbond.techsupport.domain.Party;

import java.util.Map;
import java.util.HashMap;

public final class Controllers
extends Object
{
    private final static Map<Class<?>, Controller> mappingControllerInterfaceToImplementation = new HashMap<Class<?>, Controller> ( );

    private Controllers ( )
    {
    }

    public static <T extends Controller> T getInstance (Class<T> typeOfController) throws ControllerIsMissing
    {
        assert typeOfController != null : "No type of controller provided.";
        T controller = typeOfController.cast(Controllers.mappingControllerInterfaceToImplementation.get(typeOfController));
        if (controller == null)
        {
            throw new ControllerIsMissing (typeOfController);
        }
        return controller;
    }

    public static IssueFilteringController getIssueFilteringController (Party readingParty, IssueFilteringMode modeSelected)
    {
        return NativeIssueFilteringController.newInstance(readingParty, modeSelected);
    }
}