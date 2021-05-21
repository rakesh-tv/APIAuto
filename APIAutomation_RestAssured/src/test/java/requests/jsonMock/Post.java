package requests.jsonMock;

import Utils.Util;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.jsonMock.Posts;


import static io.restassured.RestAssured.given;

public class Post {

    public void addNewPost(Integer id, String title, String author){
        RestAssured.baseURI = Util.getBaseURL();

        Posts newPost = Posts.builder().id(id).title(title).author(author).build();

        Posts resAsUserClass =   given().
                                    contentType("application/json").
                                    body(newPost).
                                when().
                                    post("/posts").
                                then().
                                    statusCode(201).extract().response().as(Posts.class);

    }

    public Posts[] getAllPosts(){
        RestAssured.baseURI = Util.getBaseURL();

        Posts[] posts =   given().
                    contentType("application/json").
                when().
                    get("/posts").
                then().
                    statusCode(200).extract().response().as(Posts[].class);
        return posts;

    }

    public Response getPost(Integer id){
        RestAssured.baseURI = Util.getBaseURL();

        Response res =   given().
                    contentType("application/json").
                when().
                    get("/posts/" + id.toString()).
                then().
                    extract().response();
        return res;

    }

    public Posts updatePost(Integer id, String title, String author){
        RestAssured.baseURI = Util.getBaseURL();
        Posts newPost = Posts.builder().id(id).title(title).author(author).build();
        Posts post =   given().
                    contentType("application/json").
                    body(newPost).
                when().
                    put("/posts"+id.toString()).
                then().
                    statusCode(200).extract().response().as(Posts.class);
        return post;
    }

    public Response deletePost(Integer postId){
        RestAssured.baseURI = Util.getBaseURL();

        Response res =   given().
                    contentType("application/json").
                when().
                    delete("/posts/" + postId.toString()).
                then().
                    extract().response();
        return res;

    }

}
