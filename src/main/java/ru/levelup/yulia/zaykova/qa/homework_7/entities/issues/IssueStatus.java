package ru.levelup.yulia.zaykova.qa.homework_7.entities.issues;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IssueStatus {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;

    private String name;
}
