package ch.protonmail.vladyslavbond.techsupport.web.views;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

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
        try
        {
            String nameOfTemplate = "index";
            BufferedReader br = new BufferedReader(new InputStreamReader (TemplateFactory.class.getClassLoader( ).getResourceAsStream(nameOfTemplate + ".html")));
            br.readLine( );
        } catch (IOException e) {
            fail(e.getMessage( ));
        }
    }
}
