package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({SumTest.class, SubtractTest.class, MultTest.class, DivisionTest.class, SqrtTest.class})
public class AllOperationsTestSuite {}
