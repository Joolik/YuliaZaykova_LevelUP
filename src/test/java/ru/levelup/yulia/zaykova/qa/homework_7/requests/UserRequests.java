package ru.levelup.yulia.zaykova.qa.homework_7.requests;

import io.restassured.response.Response;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.users.User;

import static io.restassured.RestAssured.given;

public class UserRequests extends BaseRequests {

    private static final String USER_ENDPOINT = "/users/";


    public static Response addUser(User user) {
        return given()
                .spec(getRequestSpecificationWithBody(user))
                .when()
                .post(USER_ENDPOINT);
    }


    public static Response deleteUser(long userId) {
        return given()
                .spec(getRequestSpecification())
                .when()
                .delete(USER_ENDPOINT + userId);
    }
}
