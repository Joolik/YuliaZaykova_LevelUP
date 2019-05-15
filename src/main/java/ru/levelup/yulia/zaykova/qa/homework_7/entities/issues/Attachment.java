package ru.levelup.yulia.zaykova.qa.homework_7.entities.issues;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import ru.levelup.yulia.zaykova.qa.homework_7.utils.HelperFiles;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Attachment {

    private String name;
    private String content;

    public Attachment(String filePath, String name) {
        this.name = name;
        this.content = HelperFiles.convertFileToBase64(filePath + name);
    }

}
