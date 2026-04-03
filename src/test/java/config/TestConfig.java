package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class TestConfig {

    private static String token;

    // 请求配置
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key", "reqres_3a3a43e2ced2455d9595965443c535cc")
                .log(LogDetail.BODY)
                .addFilter(new AllureRestAssured())
                .build();
    }

    // 响应配置
    public static ResponseSpecification getResponseSpec(Integer statusCode) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.ALL);
        if (statusCode != 204) {
            builder.expectContentType(ContentType.JSON);
        }
        return builder.build();
    }

    // 获取token
    public void getToken() {
        if (token != null) {
            return;
        }
        token = given()
                .spec(getRequestSpec())
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                .when()
                .post("/login")
                .path("token");
    }
}
