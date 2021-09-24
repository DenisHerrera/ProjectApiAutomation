package testPut;

import basePage.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.Test;
import pojo.PutUserRequest;
import pojo.PutUserResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PutTest extends BaseTest {

    @Test
    public void userUpdateTestS1() {

        PutUserRequest user = new PutUserRequest();
        user.setName("morpheus");
        user.setJob("zion resident");

        PutUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .put("users/2")
                        .then()
                        .extract()
                        .body()
                        .as(PutUserResponse.class);

        assertThat(userResponse.getName(), equalTo("morpheus"));
        assertThat(userResponse.getJob(), equalTo("zion resident"));

    }
    @Test
    public void responseServerPutMethodTest () {

        PutUserRequest user = new PutUserRequest();
        user.setName("morpheus");
        user.setJob("zion resident");

        PutUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .put("users/2")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .as(PutUserResponse.class);
    }

    @Test
    public void bodyResponseTest () {

        PutUserRequest user = new PutUserRequest();
        user.setName("morpheus");
        user.setJob("zion resident");

        PutUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .put("users/2")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .contentType(equalTo("application/json; charset=utf-8"))
                        .extract()
                        .as(PutUserResponse.class);
    }

    @Test
    public void responseUpdatedAtTest() {

        PutUserRequest user = new PutUserRequest();
        user.setName("morpheus");
        user.setJob("zion resident");

        PutUserResponse userResponse =
                given()
                        .when()
                        .body(user)
                        .put("users/2")
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body("updatedAt",notNullValue())
                        .extract()
                        .body()
                        .as(PutUserResponse.class);


    }
}
