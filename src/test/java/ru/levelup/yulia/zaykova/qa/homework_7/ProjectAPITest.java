package ru.levelup.yulia.zaykova.qa.homework_7;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.levelup.yulia.zaykova.qa.homework_7.data.TestData;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.projects.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.levelup.yulia.zaykova.qa.homework_7.requests.BaseRequests.getResponseObject;
import static ru.levelup.yulia.zaykova.qa.homework_7.requests.ProjectRequests.*;

public class ProjectAPITest {

    /*
     *  Создание проекта
     */
    @Test(dataProvider = "projectDataProvider", dataProviderClass = TestData.class)
    public void addProjectTest(final Project project)  {
        Response response = addProject(project);
        response.then()
                .log().status()
                .statusCode(201);
        Project responseProject = getResponseObject(response.getBody().asString(), Project.class);

        assertThat(project.getName(), equalTo(responseProject.getName()));
    }


    /*
     *  Создание подпроекта.
     *  Создаются 2 проекта. Второй проект добавляется как подпроект в первый проект.
     */
    @Test
    public void addSubprojectTest() {

        List<Project> projectsList = TestData.subprojectData();
        assertThat("Неверные тестовые данные", projectsList.size(), greaterThanOrEqualTo(2));

        // Создание первого проекта
        Response responseMainProject = addProject(projectsList.get(0));
        responseMainProject.then()
                .statusCode(201);
        Project projectMain = getResponseObject(responseMainProject.getBody().asString(), Project.class);

        // Создание второго проекта
        Response responseSubProject = addProject(projectsList.get(1));
        responseSubProject.then()
                .statusCode(201);
        Project projectSub = getResponseObject(responseSubProject.getBody().asString(), Project.class);

        // Создание объекта SubProject
        Project project = new Project();
        project.setName(projectSub.getName());
        SubProject subProject = new SubProject();
        subProject.setProject(project);
        subProject.setInheritParent(true);

        // Добавление проекта projectSub в проект projectMain
        Response response = addSubproject(subProject, projectMain.getId());
        String resStatusLine = response
                .then()
                .log().status()
                .statusCode(204)
                .extract()
                .statusLine();
        String substring = "Subproject '" + projectSub.getId() + "' added to project '" + projectMain.getId() + "'";
        assertThat(resStatusLine, containsString(substring));
    }

    /*
     *  Удаление несуществующего проекта
     */
    @Test
    public void deleteProjectNonExistTest() {
        deleteProject(getMaxProjectId() + 100)
                .then()
                .log().status()
                .statusCode(403);
    }
}
