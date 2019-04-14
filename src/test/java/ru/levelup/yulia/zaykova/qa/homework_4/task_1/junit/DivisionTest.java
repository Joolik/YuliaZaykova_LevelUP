package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import ru.levelup.qa.at.calculator.Calculator;

import static org.junit.Assert.assertEquals;

public class DivisionTest {

    private Calculator calculator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        System.out.println("Div (JUnit4): Create object Calculator");
        calculator = new Calculator();
    }

    @Category(MultDivCategory.class)
    @Test
    public void divLong1Test() {
        assertEquals(2, calculator.div(-25, -9));
    }

    @Category(MultDivCategory.class)
    @Test
    public void divLong2Test() {
        assertEquals(13, calculator.div(42500000000L, 3200000000L));
    }

    @Category(MultDivCategory.class)
    @Test
    public void divLongZeroTest() throws Exception{
        thrown.expect(Exception.class);
        thrown.expectMessage("by zero");
        calculator.div(10, 0);
    }

    @Category(MultDivCategory.class)
    @Test
    public void divDouble1Test() {
        assertEquals(2.245129, calculator.div(8.527, 3.798), 1E-6);
    }

    @Category(MultDivCategory.class)
    @Test
    public void divDoubleZeroTest() {
        assertEquals(Double.POSITIVE_INFINITY, calculator.div(10.0, 0.0), 0.0001);
    }

    @After
    public void tearDown() {
        System.out.println("Div (JUnit4): Delete object Calculator");
        calculator = null;
    }

}
