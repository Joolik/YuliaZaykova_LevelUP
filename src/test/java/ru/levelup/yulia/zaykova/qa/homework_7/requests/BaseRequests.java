package ru.levelup.yulia.zaykova.qa.homework_7.requests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Map;

public abstract class BaseRequests {

    private static final String TEST_BASE_URI = "http://khda91.fvds.ru/mantisbt/api/rest";
    private static final String AUTH_TOKEN = "XNd5tIXXB7U8FV4C8w0nao0WYr5S0nN7";

    // TODO почему static?
    // TODO 2Dmitry: потому что вызывается в статических методах в UserRequests, ProjectRequests и IssueRequests.
    // TODO Без этого не работает.
    protected static RequestSpecification getRequestSpecificationWithBody(Object obj) {
            return new RequestSpecBuilder()
                .setBaseUri(TEST_BASE_URI)
                .addHeader("Authorization", AUTH_TOKEN)
                .setContentType(ContentType.JSON)
                .setBody(obj)
                .log(LogDetail.ALL)
                .build();
    }

    protected static RequestSpecification getRequestSpecificationWithBody(Object obj, Map mapPathParams) {
        return new RequestSpecBuilder()
                .setBaseUri(TEST_BASE_URI)
                .addHeader("Authorization", AUTH_TOKEN)
                .setContentType(ContentType.JSON)
                .addPathParams(mapPathParams)
                .setBody(obj)
                .log(LogDetail.ALL)
                .build();
    }

    protected static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(TEST_BASE_URI)
                .addHeader("Authorization", AUTH_TOKEN)
                .build();
    }

    public static <T> T getResponseObject(String responseJSON, Class<T> responseClass) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        T responseObject = null;
        try {
            responseObject = mapper.readValue(responseJSON, responseClass);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return responseObject;
        }
    }

}
