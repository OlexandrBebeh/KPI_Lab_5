package endpoints;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ImgurBaseEndpoints {
    public Response getUserByName(String name) {
        return given()
                .pathParam("name", name)
                .when()
                .get(Config.USER_BY_NAME)
                .then().extract().response();
    }

    public Response getCommentByID(String commentId,String galleryId) {
        return given()
                .pathParam("gallery_id", galleryId)
                .pathParam("comment_id", commentId)
                .when()
                .get(Config.COMMENT_BY_ID)
                .then().extract().response();
    }
    private RequestSpecification given(){
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.IMGUR_BASE_URL)
                .contentType(ContentType.JSON);
    }
}
