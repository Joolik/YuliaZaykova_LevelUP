package ru.levelup.yulia.zaykova.qa.homework_7.entities.issues;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import ru.levelup.yulia.zaykova.qa.homework_7.entities.projects.Project;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName(value = "issue")

public class Issue {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long id;

    private String summary;
    private String description;

    @JsonIgnoreProperties(value = "enabled")
    private Project project;

    private Category category;
    private Attachment[] files;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private IssueStatus status;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Handler handler;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Priority priority;


}
