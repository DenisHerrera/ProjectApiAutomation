package testPost;

import static io.restassured.RestAssured.*;

import basePage.BaseTest;

import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.HttpStatus;
import org.junit.Test;
import pojo.PostUserRequest;
import pojo.PostUserResponse;
import pojo.RequestRegister;
import pojo.ResponseRegister;


import static org.hamcrest.Matchers.*;
import static io.restassured.path.json.JsonPath.from;

public class PostRegisterTest extends BaseTest {

    @Test
    public void postRegisterValidTest() {

        given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("/register")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    @Test
    public void postRegisterInvalidPasswordTest() {

        RequestRegister user = new RequestRegister();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("   ");

        given()
                .when()
                .body(user)
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }

    @Test
    public void postRegisterNotDefinedUserTest() {

        RequestRegister user = new RequestRegister();
        user.setEmail("juan.perez@gmail.com");
        user.setPassword("123456789");

        ResponseRegister response = given()
                .when()
                .body(user)
                .post("/register")
                .then()
                .statusCode(400)
                .extract().body().as(ResponseRegister.class);
        assertThat(response.getError(),equalTo("Note: Only defined users succeed registration"));

    }

    @Test
    public void postRegisterValidTest2() {

        RequestRegister user = new RequestRegister();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        ResponseRegister response = given()
                .when()
                .body(user)
                .post("/register")
                .then()
                .statusCode(200)
                .extract().body().as(ResponseRegister.class);

        assertThat( response.getId(), equalTo(4));
        assertThat( response.getToken(), equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void postRegisterWithoutPasswordTest1() {

        given()
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .post("/register")
                .then()
                .statusCode(400)
                .body("error", notNullValue());
    }
    @Test
    public void serverResponseTest(){

        PostUserRequest user = new PostUserRequest();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        PostUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .post("login")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(PostUserResponse.class);
    }
    @Test
    public void responseTokenTest() {

        PostUserRequest user = new PostUserRequest();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        PostUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .post("login")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body("token",notNullValue())
                        .extract()
                        .as(PostUserResponse.class);

        assertThat(userResponse.getToken(), equalTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void userBodyResponseTest() {

        PostUserRequest user = new PostUserRequest();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        PostUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .post("login")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(equalTo("application/json; charset=utf-8"))
                        .extract()
                        .body()
                        .as(PostUserResponse.class);
    }
}


