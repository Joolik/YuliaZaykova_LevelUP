package ru.levelup.yulia.zaykova.qa.homework_7;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.levelup.yulia.zaykova.qa.homework_7.data.TestData;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.issues.Issue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.levelup.yulia.zaykova.qa.homework_7.requests.BaseRequests.getResponseObject;
import static ru.levelup.yulia.zaykova.qa.homework_7.requests.IssueRequests.*;

public class IssueAPITest {

    /*
     * Создание issue с attachment
     */
    @Test(dataProvider = "issueDataProvider", dataProviderClass = TestData.class)
    public void addIssueTest(final Issue issue) {
        Response response = addIssue(issue);
        response.then()
                .log().status()
                .statusCode(201);
        Issue responseIssue = getResponseObject(response.getBody().asString(), Issue.class);
        assertThat(issue.getSummary(), equalTo(responseIssue.getSummary()));
    }

    /*
     * Обновление issue
     */
    @Test(dataProvider = "issueUpdateDataProvider", dataProviderClass = TestData.class)
    public void updateIssueTest(final Issue issue, final Issue issueUpdated) {

        // Создание issue
        Response response = addIssue(issue);
        response.then()
                .log().status()
                .statusCode(201);
        Issue responseIssue = getResponseObject(response.getBody().asString(), Issue.class);

        // Обновление созданного issue
        updateIssue(issueUpdated, responseIssue.getId())
                .then()
                .log().status()
                .statusCode(200);
    }

    /*
     * Обновление несуществующего issue
     */
    @Test(dataProvider = "issueUpdateDataProvider", dataProviderClass = TestData.class)
    public void updateIssueNonExistTest(final Issue issue, final Issue issueUpdated) {
        updateIssue(issueUpdated, getMaxIssueId() + 100)
                .then()
                .log().status()
                .statusCode(404);
    }

}
