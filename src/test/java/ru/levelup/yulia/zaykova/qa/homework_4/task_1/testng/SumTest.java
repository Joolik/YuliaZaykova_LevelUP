package ru.levelup.yulia.zaykova.qa.homework_4.task_1.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.qa.at.calculator.Calculator;

import static org.testng.Assert.assertEquals;

@Test(groups = {"sum-sub-test"})
public class SumTest {

   private Calculator calculator;

    @BeforeMethod (alwaysRun = true)
    public void setUp() {
        System.out.println("Sum (TestNG) : create Calculator");
        calculator = new Calculator();
    }

    @Test (dataProvider = "sumLongData", dataProviderClass = CalcDataProviders.class)
    public void sumLongTest(long arg1, long arg2, long result) {
        assertEquals(calculator.sum(arg1,arg2), result);
    }

    @Test (dataProvider = "sumDoubleData", dataProviderClass = CalcDataProviders.class)
    public void sumDoubleTest(double arg1, double arg2, double result, double delta) {
        assertEquals(calculator.sum(arg1,arg2), result, delta);
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        System.out.println("Sum (TestNG) : delete Calculator");
        calculator = null;
    }
}
