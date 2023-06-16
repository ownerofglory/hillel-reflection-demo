package ua.ithillel.reflect.calc;

import ua.ithillel.reflect.test.AfterSuite;
import ua.ithillel.reflect.test.BeforeSuite;
import ua.ithillel.reflect.test.Test;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeSuite
    public void setUp() {
        System.out.println("staring test");
        calculator = new Calculator();
    }

    @Test()
    public void add_test() {
        double a = 10;
        double b = 20;
        double expected = 30;

        double actual = calculator.add(a, b);

        assert actual == expected;
    }

    @Test
    public void subtract_test() {
        double a = 10;
        double b = 20;
        double expected = -10;

        double actual = calculator.add(a, b);

        assert actual == expected;
    }

    @AfterSuite
    public void tearDown() {
        //
        System.out.println("tests done");
    }
}
