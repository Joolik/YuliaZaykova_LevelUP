package ru.levelup.yulia.zaykova.qa.homework_4.task_1.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelup.qa.at.calculator.Calculator;

import static org.testng.Assert.assertEquals;

@Test(groups = {"mult-div-test"})
public class DivTest {

    private Calculator calculator;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("Div (TestNG) : create Calculator");
        calculator = new Calculator();
    }

    @Test(dataProvider = "divLongData", dataProviderClass = CalcDataProviders.class)
    public void multLongTest(long arg1, long arg2, long result) {
        assertEquals(calculator.div(arg1,arg2), result);
    }

    @Test (dataProvider = "divDoubleData", dataProviderClass = CalcDataProviders.class)
    public void multDoubleTest(double arg1, double arg2, double result, double delta) {
        assertEquals(calculator.div(arg1,arg2), result, delta);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("Div (TestNG) : delete Calculator");
        calculator = null;
    }
}
