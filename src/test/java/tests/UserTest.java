package tests;

import io.qameta.allure.*;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static config.TestConfig.getResponseSpec;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static services.UserService.*;

@Epic("用户系统")
@Feature("用户管理功能")
public class UserTest {

    @Test
    @Story("查询用户列表")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetUserList() {
        getUserListResponse(1)
                .then()
                .spec(getResponseSpec(200))
                .body("page", equalTo(1))
                .body("data.size()", equalTo(6))
                .body("data.id", notNullValue());
    }


    @ParameterizedTest
    @CsvSource({"1,George", "2,Janet", "3,Emma"})
    @Story("查询单个用户")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetUser(int id, String firstName) {
        User user = getUser(id);
        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    @Story("创建用户")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateUser() {
        createUserResponse(new User("张三", "测试人员"))
                .then()
                .spec(getResponseSpec(201))
                .body("id", notNullValue())
                .body("name", equalTo("张三"))
                .body("job", equalTo("测试人员"));
    }

    @Test
    @Story("删除用户")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteUser() {
        deleteUserResponse(1)
                .then()
                .spec(getResponseSpec(204));
    }
}
