package ru.levelup.yulia.zaykova.qa.homework_4.task_1.junit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(MultDivCategory.class)
@Suite.SuiteClasses({MultTest.class, DivisionTest.class})
public class MultDivTestSuite {
}
