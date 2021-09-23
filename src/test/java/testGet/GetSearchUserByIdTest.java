package testGet;

import basePage.BaseTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.config.LogConfig.logConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetSearchUserByIdTest extends BaseTest {
    @Test
    public void requestSearchSingleUserByCorrectId(){
        RestAssured
                .given().queryParam("format","json")
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("/users/2").
                then().log().all()
                .assertThat().statusCode(is(equalTo(HttpStatus.SC_OK)));

    }
    @Test
    public void requestSearchSingleUserByIncorrectId(){
        RestAssured
                .given().queryParam("format","json")
                .config(config().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .when().get("/users/23").
                then().log().all()
                .assertThat().statusCode(is(equalTo(HttpStatus.SC_NOT_FOUND)));

    }


}
