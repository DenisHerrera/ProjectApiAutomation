package testGet;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

import basePage.BaseTest;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetMethodTest extends BaseTest {

    @Test
    public void getUsertest(){

        given()
                .when()
                .get("/users?page=1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data[2].first_name", equalTo ("Emma"));
    }

    @Test
    public void testGetId(){

        given()
                .when()
                .get("/users?page=1")
                .then()
                .statusCode(200)
                .body("data.id", hasItems(1,2,3,4,5,6));
    }

    // content type, headers, response
    @Test
    public void getAllUsersTest(){

        Response response = given()
                .get("users?page=2");

        Headers headers = response.getHeaders();
        int statusCode = response.getStatusCode();
        String body = response.getBody().asString();
        String contentType = response. getContentType();

        assertThat( statusCode, equalTo(HttpStatus.SC_OK));

        System.out.println("Headers:" +headers);
        System.out.println("body:" +body);
        System.out.println("content Type:" +contentType);

        System.out.println("-------------------------------");
        System.out.println("content Type:" + (headers.get("Content-Type")));
        System.out.println("Transfer encoding:" +(headers.get("Transfer-Encoding")));
    }
    //  jsonPath
    @Test
    public void getAllUserTest2(){
        String response = given()
                .when().get("users?page=2").then().extract().body().asString();
        int page = from(response).get("page");
        int totalPages = from(response).get("total_pages");
        int idFirstUser = from(response).get("data[0].id");

        System.out.println("Page: "+page);
        System.out.println("Total Pages: "+totalPages);
        System.out.println("Id first user: "+idFirstUser);

        List<Map> usersWithIdGreaterThan10 = from(response).get("data.findAll { user -> user.id > 10}");
        String email = usersWithIdGreaterThan10.get(0).get("email").toString();

        List<Map> user = from(response).get("data.findAll { user -> user.id>10 && user.last_name == 'Howell'}");
        int id = Integer.valueOf(user.get(0). get("id").toString());
    }

}

