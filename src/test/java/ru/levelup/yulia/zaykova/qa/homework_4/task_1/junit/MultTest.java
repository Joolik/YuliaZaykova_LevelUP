package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import ru.levelup.qa.at.calculator.Calculator;

import static org.junit.Assert.assertEquals;

public class MultTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        System.out.println("Mult (JUnit4): Create object Calculator");
        calculator = new Calculator();
    }

    @Category(MultDivCategory.class)
    @Test
    public void multLong1Test() {
        assertEquals(15, calculator.mult(5, 3));
    }

    @Category(MultDivCategory.class)
    @Test
    public void multLong2Test() {
        assertEquals(96, calculator.mult(-12, -8));
    }

    @Test
    public void multLong3Test() {
        assertEquals(-450, calculator.mult(-1, 450));
    }

    @Test
    public void multLong4Test() {
        assertEquals(0, calculator.mult(0, 0));
    }

    @Category(MultDivCategory.class)
    @Test
    public void multLong5Test() {
        assertEquals(14400000000000L, calculator.mult(4500000000L, 3200L));
    }

    @Category(MultDivCategory.class)
    @Test
    public void multDouble1Test() {
        assertEquals(32.385546, calculator.mult(8.527, 3.798), 0.000001);
    }

    @Category(MultDivCategory.class)
    @Test
    public void multDouble2Test() {
        assertEquals(0.00, calculator.mult(0.00, 0.00), 0.001);
    }

    @Test
    public void multLongOverflowTest() {
        assertEquals(Long.MAX_VALUE*2, calculator.mult(Long.MAX_VALUE, 2));
    }

    @After
    public void tearDown() {
        System.out.println("Mult (JUnit4): Delete object Calculator");
        calculator = null;
    }
}
