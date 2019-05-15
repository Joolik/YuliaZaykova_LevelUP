package ru.levelup.yulia.zaykova.qa.homework_7.data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import ru.levelup.yulia.zaykova.qa.homework_7.enums.ViewState;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.issues.*;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.projects.*;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.users.*;
import ru.levelup.yulia.zaykova.qa.homework_7.utils.HelperFiles;

import java.util.*;

public class TestData {

    private static final String PATH_DATA_FILES = "src/test/resources/homework_7/";
    private static final String PATH_ATTACHMENT = "src/test/resources/homework_7/attachments/";

    public static final String USER_DATA_FILE = "user_data.csv";
    public static final String PROJECT_DATA_FILE = "project_data.csv";
    public static final String SUBPROJECT_DATA_FILE = "subproject_data.csv";

    @DataProvider
    public Object[][] userDataProvider() {
        List<User> users = new ArrayList<>();
        List<Map<String, String>> records = HelperFiles.getRecordsFromCSVFile(PATH_DATA_FILES + USER_DATA_FILE);
        User user;
        for (Map<String, String> map : records) {
            user = createUserByMap(map);
            users.add(user);
        }
        Object[][] usersArray = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            usersArray[i][0] = users.get(i);
        }
        return usersArray;
    }


    private User createUserByMap(Map<String, String> userParams) {
        UserAccessLevel ual = new UserAccessLevel();
        ual.setName(userParams.get("accessLevelName"));

        User user = new User();
        user.setAccessLevel(ual);
        user.setUsername(userParams.get("username") + RandomStringUtils.randomNumeric(6));
        user.setPassword(userParams.get("password"));
        user.setEmail(userParams.get("email"));
        user.setRealName(userParams.get("realName"));
        user.setUserProtected(Boolean.parseBoolean(userParams.get("userProtected")));
        user.setEnabled(Boolean.parseBoolean(userParams.get("userEnabled")));
        return user;
    }


    @DataProvider
    public Object[][] projectDataProvider() {
        List<Project> projects = new ArrayList<>();
        List<Map<String, String>> records = HelperFiles.getRecordsFromCSVFile(PATH_DATA_FILES + PROJECT_DATA_FILE);
        Project project;
        for (Map<String, String> map : records) {
            project = createProjectByMap(map);
            projects.add(project);
        }
        Object[][] projectsArray = new Object[projects.size()][1];
        for (int i = 0; i < projects.size(); i++) {
            projectsArray[i][0] = projects.get(i);
        }
        return projectsArray;
    }

    public static List<Project> subprojectData() {
        List<Project> projects = new ArrayList<>();
        List<Map<String, String>> records = HelperFiles.getRecordsFromCSVFile(PATH_DATA_FILES + SUBPROJECT_DATA_FILE);
        Project project;
        for (Map<String, String> map : records) {
            project = createProjectByMap(map);
            projects.add(project);
        }
        return projects;
    }

    private static Project createProjectByMap(Map<String, String> projectParams) {
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setId(Integer.parseInt(projectParams.get("statusId")));
        projectStatus.setName(projectParams.get("statusName"));
        projectStatus.setLabel(projectParams.get("statusLabel"));

        ProjectViewState projectViewState = new ProjectViewState();
        projectViewState.setId(ViewState.getIdByName(projectParams.get("viewStateName")));
        projectViewState.setName(projectParams.get("viewStateName"));
        projectViewState.setLabel(projectParams.get("viewStateName"));

        Project project = new Project();
        project.setName(projectParams.get("name") + RandomStringUtils.randomNumeric(6));
        project.setDescription(projectParams.get("description"));
        project.setEnabled(Boolean.parseBoolean(projectParams.get("enabled")));
        project.setFilePath(projectParams.get("filePath"));
        project.setStatus(projectStatus);
        project.setViewState(projectViewState);
        return project;
    }


    @DataProvider
    public Object[][] issueDataProvider() {
        Issue issue = createIssue("Test summary", "Test description for issue (with attachment).", "1", "General");
        return new Object[][]{
                {issue}
        };
    }

    @DataProvider
    public Object[][] issueUpdateDataProvider() {
        Issue issue = createIssue("Test summary", "Test description for issue (with attachment).", "1", "General");
        Issue issueUpdated = createIssueUpdated("Test summary UPDATED", "Test description for issue (UPDATED).", "resolved", "immediate");
        return new Object[][]{
                {issue, issueUpdated}
        };
    }

    private Issue createIssue(String summary, String description, String categoryId, String categoryName) {
        Issue issue = new Issue();

        Project project = new Project();
        project.setId(131);
        project.setName("ProjectYZ01_test");

        Attachment[] arrayFile = {new Attachment(PATH_ATTACHMENT, "log.txt"), new Attachment(PATH_ATTACHMENT, "smile1.png")};

        Category category = new Category();
        category.setId(Integer.parseInt(categoryId));
        category.setName(categoryName);

        issue.setSummary(summary + " " + RandomStringUtils.randomNumeric(5));
        issue.setDescription(description);
        issue.setCategory(category);
        issue.setProject(project);
        issue.setFiles(arrayFile);
        return issue;
    }

    private Issue createIssueUpdated(String summary, String description, String statusName, String priorityName) {
        Issue issue = new Issue();

        IssueStatus status = new IssueStatus();
        status.setName(statusName);

        Priority priority = new Priority();
        priority.setName(priorityName);

        issue.setSummary(summary + " " + RandomStringUtils.randomNumeric(5));
        issue.setDescription(description);
        issue.setStatus(status);
        issue.setPriority(priority);
        return issue;
    }

}
