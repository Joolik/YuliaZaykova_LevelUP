package ru.levelup.yulia.zaykova.qa.homework_7.entities.users;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName(value = "user")
public class User {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long id;

    @JsonAlias("name")
    private String username;
    private String password;

    @JsonProperty("real_name")
    private String realName;
    private String email;

    @JsonProperty("access_level")
    private UserAccessLevel accessLevel;
    private boolean enabled;

    @JsonProperty("protected")
    private boolean userProtected;
}
