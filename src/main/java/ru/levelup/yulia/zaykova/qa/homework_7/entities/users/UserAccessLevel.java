package ru.levelup.yulia.zaykova.qa.homework_7.entities.users;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccessLevel {
    private String name;
}