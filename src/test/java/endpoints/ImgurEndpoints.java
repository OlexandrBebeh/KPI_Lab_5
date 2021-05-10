package endpoints;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ImgurEndpoints {

    public Response getImageByID(String id) {
        return given()
                .header("Authorization", Config.USER_ID)
                .pathParam("id", id)
                .when()
                .get(Config.IMAGE_BY_ID)
                .then().extract().response();
    }
    public Response deleteImageByID(String imageDeleteHash) {
        return given()
                .header("Authorization",Config.USER_ID)
                .pathParam("id", imageDeleteHash)
                .when()
                .delete(Config.IMAGE_BY_ID)
                .then().extract().response();
    }
    public Response postImageByURL(String url) {
        return given()
                .header("Authorization",Config.USER_ID)
                .formParam("image",url)
                .when()
                .post("/image")
                .then().extract().response();
    }

    private RequestSpecification given(){
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.IMGUR_API_URL);
    }
}
