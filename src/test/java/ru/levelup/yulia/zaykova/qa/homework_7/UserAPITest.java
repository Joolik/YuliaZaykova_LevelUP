package ru.levelup.yulia.zaykova.qa.homework_7;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.levelup.yulia.zaykova.qa.homework_7.data.TestData;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.users.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static ru.levelup.yulia.zaykova.qa.homework_7.requests.BaseRequests.getResponseObject;
import static ru.levelup.yulia.zaykova.qa.homework_7.requests.UserRequests.*;

public class UserAPITest {

    private long userId;

    /*
     * Создание пользователя (двух пользователей)
     */
    @Test(dataProvider = "userDataProvider", dataProviderClass = TestData.class)
    public void addUserTest(final User user) {
        Response response = addUser(user);
        response.then()
                .log().status()
                .statusCode(201)
                .statusLine(containsString("User created with id"));
        User responseUser = getResponseObject(response.getBody().asString(), User.class);

        assertThat(user.getUsername(), equalTo(responseUser.getUsername()));
        assertThat(user.getRealName(), equalTo(responseUser.getRealName()));
        assertThat(user.getEmail(), equalTo(responseUser.getEmail()));

        userId = responseUser.getId();
    }

    /*
     * Удаление последнего пользователя, созданного в тесте "addUserTest"
     */
    @Test(dependsOnMethods = "addUserTest")
    public void deleteUserTest() {
        deleteUser(userId)
        .then()
                .log().status()
                .statusCode(204);
    }

    /*
     * Удаление несуществующего пользователя (пользователя, удаленного ранее в тесте "deleteUserTest")
     */
    @Test(dependsOnMethods = "deleteUserTest")
    public void deleteUserNonExistTest() {
        deleteUser(userId)
                .then()
                .log().status()
                .statusCode(404)
                .statusLine(containsString("not found"));
    }

}
