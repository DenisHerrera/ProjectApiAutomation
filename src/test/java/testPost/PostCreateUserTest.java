package testPost;

import basePage.BaseTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import pojo.PostCreateUserRequest;
import pojo.PostCreateUserResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class PostCreateUserTest extends BaseTest {
    //the test case focus on creating a new user with a name and job specified
    @Test
    public  void userCreateRequest(){
      PostCreateUserRequest userCreate = new PostCreateUserRequest();
      userCreate.setName("morpheus");
      userCreate.setJob("leader");
        PostCreateUserResponse responseUser = RestAssured.given()
                .when()
                .body(userCreate).
                post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(PostCreateUserResponse.class);
        assertThat(responseUser.getName(), equalTo("morpheus"));
        assertThat(responseUser.getJob(), equalTo("leader"));

    }
    //the test case focus on creating a new user with a name
@Test
    public  void userCreateOnlyName(){
        PostCreateUserRequest userCreate = new PostCreateUserRequest();
        userCreate.setName("morpheus");

        PostCreateUserResponse responseUser = RestAssured.given()
                .when()
                .body(userCreate).
                post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(PostCreateUserResponse.class);
        assertThat(responseUser.getName(), equalTo("morpheus"));


    }
    //the test case focus on creating a new user with a  job specified
    @Test
    public  void userCreateOnlyJob(){
        PostCreateUserRequest userCreate = new PostCreateUserRequest();
                userCreate.setJob("trainer of QA API Automation");
        PostCreateUserResponse responseUser = RestAssured.given()
                .when()
                .body(userCreate).
                post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(PostCreateUserResponse.class);
        assertThat(responseUser.getJob(), equalTo("trainer of QA API Automation"));

    }
    //the test case focus on creating a new user with empty information
    @Test
    public  void userCreateEmpty(){
        PostCreateUserRequest userCreate = new PostCreateUserRequest();
        userCreate.setName("");
        userCreate.setJob("");
        PostCreateUserResponse responseUser = RestAssured.given()
                .when()
                .body(userCreate).
                post("/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(PostCreateUserResponse.class);
        assertThat(responseUser.getName(), equalTo(""));
        assertThat(responseUser.getJob(), equalTo(""));

    }
    @Test
    public void testPostUserEmpty(){
       // baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "");
        map.put("job", "");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void testPostUserOnlyName(){
        //baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name", "Alejandra");
        //map.put("job", "Costumer Success");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void testPostUserOnlyJob(){
        //baseURI = "https://reqres.in/api";
        Map<String, Object> map = new HashMap<String,Object>();
        //map.put("name", "Alejandra");
        map.put("job", "Costumer Success");
        given()
                .log().all()
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}

