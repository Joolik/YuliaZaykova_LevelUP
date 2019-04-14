package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SumSubCategory.class)
@Suite.SuiteClasses({SumTest.class, SubtractTest.class})
public class SumSubTestSuite {
}
