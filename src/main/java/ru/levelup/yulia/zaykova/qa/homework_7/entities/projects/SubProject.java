package ru.levelup.yulia.zaykova.qa.homework_7.entities.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubProject {

    @JsonIgnoreProperties(value = "enabled")
    private Project project;

    @JsonProperty("inherit_parent")
    private boolean inheritParent;
}
