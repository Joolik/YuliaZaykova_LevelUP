package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.levelup.qa.at.calculator.Calculator;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SqrtTest {

    private double arg, result, delta;

    private Calculator calculator;

    public SqrtTest(double arg, double result, double delta) {
        this.arg = arg;
        this.result = result;
        this.delta = delta;
    }

    @Parameterized.Parameters (name = "{index}: sqrt({0})={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {4, 2, 0.001},
                {0.0, 0.0, 0.001},
                {21.7, 4.65832587954, 1E-11},
                {-4.0, Math.sqrt(-4.0), 0.001},
                {1, 1, 0.001}
        });
    }

    @Before
    public void setUp() {
        System.out.println("Sqrt (JUnit4): Create object Calculator");
        calculator = new Calculator();
    }

    @Test
    public void sqrtTest() {
        assertEquals(result, calculator.sqrt(arg), delta);
    }

    @After
    public void tearDown() {
        System.out.println("Sqrt (JUnit4): Delete object Calculator");
        calculator = null;
    }
}
