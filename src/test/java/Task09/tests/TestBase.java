package Task09.tests;

import Task09.app.Application;
import org.junit.After;
import org.junit.Before;

public class TestBase {
    public Application app;

    @Before
    public void testSetup() {
        app = new Application();
    }

    @After
    public void testCleanup(){
        app.quit();
    }
}
