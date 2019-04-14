package ru.levelup.yulia.zaykova.qa.homework_4.task_1.testng;

import org.testng.annotations.DataProvider;

public class CalcDataProviders {

    @DataProvider (name = "sumLongData")
    public static Object[][] sumLongDataProvider() {
            return new Object[][] {
                    {5, 3, 8},
                    {-12, -8, -20},
                    {0, 0, 0},
                    {4500000000L, 3200750120L, 7700750120L},
                    {-5, 12, 7},
                    {85, -102, -17},
                    {0, 5, 5},
                    {-2, 0, -2}
            };
    }

    @DataProvider (name = "sumDoubleData")
    public static Object[][] sumDoubleDataProvider() {
        return new Object[][] {
                {5.5, 3.2, 8.7, 0.01},
                {-12.0, -8.0, -20.0, 0.01},
                {0.0, 0.0, 0.0, 0.01},
                {85.125, -256.4378, -171.3128, 0.0001},
                {-5.58, 5.58, 0.00, 0.01},
                {0.0, -102.3, -102.3, 0.01}
        };
    }

    @DataProvider (name = "subtLongData")
    public static Object[][] subtLongDataProvider() {
        return new Object[][] {
                {5, 3, 2},
                {-12, -8, -4},
                {0, 0, 0},
                {4500000000L, 3200750120L, 1299249880L},
                {85, -102, 187},
                {0, 5, -5},

        };
    }

    @DataProvider (name = "subtDoubleData")
    public static Object[][] subtDoubleDataProvider() {
        return new Object[][] {
                {5.5, 3.2, 2.3, 0.01},
                {0.0, 0.0, 0.0, 0.01},
                {85.125, -256.4378, 341.5628, 0.0001},
                {5.58, 5.58, 0.00, 0.01},
                {0.0, 102.3, -102.3, 0.01}
        };
    }

    @DataProvider
    public static Object[][] multLongData() {
        return new Object[][] {
                {5, 3, 15},
                {-12, -8, 96},
                {0, 0, 0},
                {4500000000L, 3200, 14400000000000L},
                {85, 1, 85},
                {0, 5, 0},

        };
    }

    @DataProvider
    public static Object[][] multDoubleData() {
        return new Object[][] {
                {5.5, 3.0, 16.5, 0.01},
                {0.0, 0.0, 0.0, 0.01},
                {85.125, -256.4378, -21829.267725, 0.000001},
                {5.58, 1.0, 5.58, 0.01},
                {0.0, 102.378, 0.0, 0.001}
        };
    }

    @DataProvider
    public static Object[][] divLongData() {
        return new Object[][] {
                {25, 9, 2},
                {42500000000L, 3200000000L, 13},
                {85, -1, -85},
                {0, 5, 0}
        };
    }

    @DataProvider
    public static Object[][] divDoubleData() {
        return new Object[][] {
                {8.527, 3.798, 2.245129, 1E-6},
                {0.0, 5.0, 0.0, 0.01},
                {9.564, 1.0, 9.564, 0.001},
                {10.0, 0.0, Double.POSITIVE_INFINITY, 0.00001}
        };
    }
}
