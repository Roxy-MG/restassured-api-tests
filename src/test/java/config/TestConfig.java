package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.time.LocalDateTime;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestConfig {

    public static final String BASE_URL = "https://reqres.in/api";
    public static final Map<String, String> X_API_KEY = Map.of("x-api-key", "reqres_3a3a43e2ced2455d9595965443c535cc");

    private static String token;
    private static LocalDateTime tokenExpireTime;
    public static final int TOKEN_VALID_TIME = 55;

    // 请求配置，默认连接超时时间5秒，读取超时时间10秒
    public static RequestSpecification getRequestSpec() {
        return getRequestSpec(5000, 10000);
    }

    // 请求配置，自定义超时时间
    public static RequestSpecification getRequestSpec(int connectionTime, int socketTime) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeaders(X_API_KEY)
                .setConfig(RestAssuredConfig.config().httpClient(
                        HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", connectionTime)
                                .setParam("http.socket.timeout", socketTime)
                ))
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
    public String getToken() {
        // 判断token是否为空/过期
        if (token != null && tokenExpireTime != null && tokenExpireTime.isAfter(LocalDateTime.now())) {
            return token;
        }
        // 并发锁
        synchronized (TestConfig.class) {
            token = given()
                    .spec(getRequestSpec())
                    .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                    .when()
                    .post("/login")
                    .path("token");
            tokenExpireTime = LocalDateTime.now().plusMinutes(TOKEN_VALID_TIME);
            return token;
        }
    }

}
