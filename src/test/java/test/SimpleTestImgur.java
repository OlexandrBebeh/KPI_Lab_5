package java.test;


import net.serenitybdd.junit.runners.SerenityRunner;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.endpoints.ImgurBaseEndpoints;
import java.endpoints.ImgurEndpoints;

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class SimpleTestImgur {
    @Test
    public void verifyStatusCode() {
        given()
                .log().uri()
                .baseUri("https://imgur.com")
                .when()
                .get("/search?q=name")
                .then()
                .statusCode(200);
    }
    @Test
    public void verifyBody() {
        given()
                .log().uri()
                .baseUri("https://imgur.com")
                .when()
                .get("/search/relevance/day?q=name&qs=thumbs")
                .then()
                .assertThat()
                .body(Matchers.notNullValue()).statusCode(200);
        ;
    }

    @Test
    public void verifyBodyUseParam() {
        given()
                .log().uri()
                .baseUri("https://imgur.com")
                .queryParam("q", "name")
                .queryParam("qs", "thumbs")
                .when()
                .get("/search/relevance/day")
                .then()
                .assertThat()
                .body(Matchers.notNullValue()).statusCode(200);
    }
    @Test
    public void verifyGetUserByID() {
        new ImgurBaseEndpoints().getUserByName("testzero")
                .then()
                .assertThat()
                .body(Matchers.notNullValue())
                .and().statusCode(200);
    }



    @Test
    public void verifyStatus404() {
        given()
                .log().uri()
                .baseUri("https://imgur.com/user/testsrt3q4c4yqxexhx3c6")
                .then()
                .statusCode(404);
    }
    @Test
    public void testGetImageById() {
        new ImgurEndpoints().getImageByID("jDGfPA0")
                .then()
                .log().headers()
                .and().log().body()
                .and().assertThat()
                .body(Matchers.notNullValue())
                .statusCode(200);
    }
    @Test
    public void testPostImageByURL() {
        new ImgurEndpoints()
                .postImageByURL("https://raw.githubusercontent.com/OlexandrBebeh/KPI-3-Test_of_program_products/master/images/superimposition_3.png")
                .then().log().headers()
                .and().log().body()
                .and().assertThat()
                .body(Matchers.notNullValue());
    }

    @Test
    public void testDelete() {
        new ImgurEndpoints()
                .deleteImageByID("D6UE01l4qCbhizl")
                .then()
                .log().headers()
                .and().log().body()
                .and().assertThat()
                .body(Matchers.notNullValue())
                .statusCode(200);
    }
}
