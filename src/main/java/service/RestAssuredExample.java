package service;

import io.restassured.http.ContentType;
import org.junit.Assert;
import java.util.List;
import static io.restassured.RestAssured.given;

public class RestAssuredExample {

    private final static String URL = "https://reqres.in/";

    public void checkAvatarAndId() {
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

}
