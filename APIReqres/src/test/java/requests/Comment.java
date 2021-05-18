package requests;

import Utils.Util;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import pojo.Comments;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Comment {

    public Response addNewComment(Integer id, String commentBody, Integer postId){
        RestAssured.baseURI = Util.getBaseURL();

        Comments newPost = Comments.builder().id(id).body(commentBody).postId(postId).build();

        Response response =   given().
                                    contentType("application/json").
                                    body(newPost).
                                when().
                                    post("/comments").
                                then().
                                    extract().response();
        return response;

    }

    public Comments[] getAllComments(){
        RestAssured.baseURI = Util.getBaseURL();

        Comments[] comments =   given().
                    contentType("application/json").
                when().
                    get("/comments").
                then().
                    statusCode(200).extract().response().as(Comments[].class);
        return comments;

    }

    public Comments getComment(Integer id){
        RestAssured.baseURI = Util.getBaseURL();

        Comments comment =   given().
                    contentType("application/json").
                when().
                    get("/comments/" + id.toString()).
                then().
                    statusCode(200).extract().response().as(Comments.class);
        return comment;

    }

    public Response updateComment(Integer commentId, String commentBody, Integer postId){
        RestAssured.baseURI = Util.getBaseURL();

        Comments newPost = Comments.builder().id(commentId).body(commentBody).postId(postId).build();

        Response response =   given().
                    contentType("application/json").
                    body(newPost).
                when().
                    put("/comments/"+commentId.toString()).
                then().
                    extract().response();
        return response;

    }

    public Response deleteComment(Integer id){
        RestAssured.baseURI = Util.getBaseURL();

        Response response =   given().
                    contentType("application/json").
                when().
                    delete("/comments/" + id.toString()).
                then().
                    extract().response();
        return response;

    }

}
