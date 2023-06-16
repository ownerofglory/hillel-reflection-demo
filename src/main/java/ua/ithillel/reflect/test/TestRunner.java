package ua.ithillel.reflect.test;

public class TestRunner {
    public static void start(Class<?> testClass) {
        // create an instance of the test class

        // get declared methods of the class

        // check annotations: @Test, @BeforeSuite, @AfterSuite

        // sort methods annotated with @Test by priority

        // check  @BeforeSuite, @AfterSuite occureance

        // in the loop
        // run method annotated with @BeforeSuite (if available)
        // run all methods annotated with @Test
        // run method annotated with @AfterSuite  (if available)
    }
}
