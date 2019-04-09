package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.levelup.qa.at.calculator.Calculator;

import static org.junit.Assert.assertEquals;

@Category(SumSubCategory.class)
public class SumTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        System.out.println("Sum (JUnit4): Create object Calculator");
        calculator = new Calculator();
    }

    @Test
    public void sumLong1Test() {
        assertEquals(8, calculator.sum(5, 3));
    }

    @Test
    public void sumLong2Test() {
        assertEquals(-20, calculator.sum(-12, -8));
    }

    @Test
    public void sumLong3Test() {
        assertEquals(0, calculator.sum(0, 0));
    }

    @Test
    public void sumLong4Test() {
        assertEquals(7700750120L, calculator.sum(4500000000L, 3200750120L));
    }

    @Test
    public void sumDouble1Test() {
        assertEquals(12.325, calculator.sum(8.527, 3.798), 0.0001);
    }

    @Test
    public void sumDouble2Test() {
        assertEquals(0.00, calculator.sum(0.00, 0.00), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void sumLongOverflowTest() {
        assertEquals(Long.MAX_VALUE + 1, calculator.sum(Long.MAX_VALUE, 1));
    }

    @After
    public void tearDown() {
        System.out.println("Sum (JUnit4): Delete object Calculator");
        calculator = null;
    }
}
