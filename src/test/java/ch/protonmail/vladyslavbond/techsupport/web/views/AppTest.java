package ch.protonmail.vladyslavbond.techsupport.web.views;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AppTest 
extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testTemplateAccessibility ( )
    {
        /*String[] paths = {"index.html", "issue.html", "select.html", "optoin.html"};
        List<String> listOfPaths = Arrays.<String>asList(paths);
        for (String pathToTemplate : listOfPaths)
        {
            try
            (
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass( ).getClassLoader( ).getResourceAsStream(pathToTemplate)));
            )
            {
                String line = reader.readLine( );
                assertTrue("Failed to read from file.", line != null && !line.isEmpty( ));
            } catch (IOException e) {
                fail(e.getMessage( ));
            }
        }*/
    }
}
