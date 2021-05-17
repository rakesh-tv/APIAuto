package requests;

import Utils.Util;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import pojo.Comments;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Comment {

    public void addNewComment(Integer id, String commentBody, Integer postId){
        RestAssured.baseURI = Util.getBaseURL();

        Comments newPost = Comments.builder().id(id).body(commentBody).postId(postId).build();

        Comments comment =   given().
                                    contentType("application/json").
                                    body(newPost).
                                when().
                                    post("/comments").
                                then().
                                    statusCode(201).extract().response().as(Comments.class);

    }

    public Comments[] getComments(){
        RestAssured.baseURI = Util.getBaseURL();

        Comments[] comments =   given().
                    contentType("application/json").
                when().
                    get("/comments").
                then().
                    statusCode(200).extract().response().as(Comments[].class);
        return comments;

    }

    public Comments updateComment(Integer commentId, String commentBody, Integer postId){
        RestAssured.baseURI = Util.getBaseURL();

        Comments newPost = Comments.builder().id(commentId).body(commentBody).postId(postId).build();

        Comments comment =   given().
                    contentType("application/json").
                    body(newPost).
                when().
                    put("/comments/"+commentId.toString()).
                then().
                    statusCode(200).extract().response().as(Comments.class);
        return comment;

    }

}
