package com.framework.utils;
import com.framework.models.AuthRequest;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.framework.config.ConfigManager.getProperty;
import static io.restassured.RestAssured.baseURI;
import static  io.restassured.RestAssured.given;

public class RequestBuilder {

public static RequestSpecification getBasicReqSpec(){

    RequestSpecification getBasicSpec = given()
            .baseUri(baseURI)
            .contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .filter(new AllureRestAssured())
            .log().all();
    return getBasicSpec;
}

    public static RequestSpecification getAuthReqSpec(){

        RequestSpecification getAuthSpec = given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie("token",getToken())
                .filter(new AllureRestAssured())
                .log().all();
        return getAuthSpec;
    }


    private static String getToken(){

    String token;
        String auth_username = getProperty("auth.username");
        String auth_password = getProperty("auth.password");

        AuthRequest authBody = AuthRequest.builder()
                .username(auth_username)
                .password(auth_password)
                .build();


        token = given()
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");

        return token;
    }



    }

