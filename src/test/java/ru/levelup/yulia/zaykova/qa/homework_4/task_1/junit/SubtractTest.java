package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.levelup.qa.at.calculator.Calculator;

import static org.junit.Assert.assertEquals;

public class SubtractTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        System.out.println("Subtract (JUnit4): Create object Calculator");
        calculator = new Calculator();
    }

    @Category(SumSubCategory.class)
    @Test
    public void subtractLong1Test() {
        assertEquals(2, calculator.sub(5, 3));
    }

    @Test
    public void subtractLong2Test() {
        assertEquals(-4, calculator.sub(-12, -8));
    }

    @Category(SumSubCategory.class)
    @Test
    public void subtractLong3Test() {
        assertEquals(0, calculator.sub(0, 0));
    }

    @Category(SumSubCategory.class)
    @Test
    public void subtarctLong4Test() {
        assertEquals(1299249880, calculator.sub(4500000000L, 3200750120L));
    }

    @Category(SumSubCategory.class)
    @Test
    public void subtarctDoubleTest() {
        assertEquals(-5.59, calculator.sub(4.52, 10.11), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void subtarctLongOverflowTest() {
        assertEquals(Long.MIN_VALUE-10, calculator.sub(Long.MIN_VALUE, 10));
    }

    @After
    public void tearDown() {
        System.out.println("Subtract (JUnit4): Delete object Calculator");
        calculator = null;
    }
}