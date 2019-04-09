package ru.levelup.yulia.zaykova.qa.homework_4.task_1.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.qa.at.calculator.Calculator;

import static org.testng.Assert.assertEquals;

@Test(groups = {"sum-sub-test"})
public class SubTest {

    private Calculator calculator;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("Subtract (TestNG) : create Calculator");
        calculator = new Calculator();
    }

    @Test(dataProvider = "subtLongData", dataProviderClass = CalcDataProviders.class)
    public void subLongTest(long arg1, long arg2, long result) {
        assertEquals(calculator.sub(arg1,arg2), result);
    }

    @Test (dataProvider = "subtDoubleData", dataProviderClass = CalcDataProviders.class)
    public void subDoubleTest(double arg1, double arg2, double result, double delta) {
        assertEquals(calculator.sub(arg1,arg2), result, delta);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("Subtract (TestNG) : delete Calculator");
        calculator = null;
    }
}
