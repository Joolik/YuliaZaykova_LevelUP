package ru.levelup.yulia.zaykova.qa.homework_7.requests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.issues.Issue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class IssueRequests extends BaseRequests {

    private static final String ISSUE_ENDPOINT = "/issues";
    private static final String ISSUE_UPDATE_ENDPOINT = "/issues/{issue_id}";

    public static Response addIssue(Issue issue) {
        return given()
                .spec(getRequestSpecificationWithBody(issue))
                .when()
                .post(ISSUE_ENDPOINT);
    }

    public static Response updateIssue(Issue newIssue, long issueId) {
        Map<String, Long> mapPathParams = new HashMap<>();
        mapPathParams.put("issue_id", issueId);
        return given()
                .spec(getRequestSpecificationWithBody(newIssue, mapPathParams))
                .when()
                .patch(ISSUE_UPDATE_ENDPOINT);
    }


    private static Response getAllIssues() {
        return given()
                .spec(getRequestSpecification())
                .when()
                .get(ISSUE_ENDPOINT + "?page_size=50&page=1");
    }

    public static long getMaxIssueId() {

        Response response = getAllIssues();

        response.then()
                .statusCode(200);

        JsonPath jsonPath = new JsonPath(response.asString());

        // Получить список id
        List<Integer> issueIdList = jsonPath.getList("issues.id");
        if (issueIdList.isEmpty()) {
            return 0;
        } else {
            issueIdList.sort(Integer::compareTo);
            return issueIdList.get(issueIdList.size() - 1);
        }
    }

}
