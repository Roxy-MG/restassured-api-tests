package services;

import io.restassured.response.Response;
import models.User;

import java.util.List;

import static config.TestConfig.*;
import static io.restassured.RestAssured.*;

public class UserService {
    private static final String USER_SERVICE_URL = "/users";

    // 查询用户列表(返回Response)
    public static Response getUserListResponse(int page) {
        return given()
                .spec(getRequestSpec())
                .queryParam("page", page)
                .when()
                .get(USER_SERVICE_URL);
    }

    // 查询用户列表(返回List<User>)
    public static List<User> getUserList(int page) {
        return getUserListResponse(page).jsonPath().getList("data", User.class);
    }

    // 查询单个用户(返回Response)
    public static Response getUserResponse(int id) {
        return given()
                .spec(getRequestSpec())
                .pathParam("id", id)
                .when()
                .get(USER_SERVICE_URL + "/{id}");
    }

    // 查询单个用户(返回User)
    public static User getUser(int id) {
        return getUserResponse(id).jsonPath().getObject("data", User.class);
    }

    // 创建用户(返回Response)
    public static Response createUserResponse(User user) {
        return given()
                .spec(getRequestSpec())
                .body(user)
                .when()
                .post(USER_SERVICE_URL);
    }

    // 创建用户(返回User)
    public static User createUser(User user) {
        return createUserResponse(user).as(User.class);
    }

    // 删除用户(返回Response)
    public static Response deleteUserResponse(int id) {
        return given()
                .spec(getRequestSpec())
                .pathParam("id", id)
                .when()
                .delete(USER_SERVICE_URL + "/{id}");
    }

}
