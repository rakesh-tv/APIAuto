package tests.jsonMock;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.jsonMock.Posts;
import requests.jsonMock.Post;

import java.util.Random;

public class TestPosts {

    Post post = new Post();
    Integer id;

    @Test(dataProvider = "verifyAddNewPost")
    public void verifyAddNewProfile(Posts newPost){
        //add new post
        id = newPost.id;
        post.addNewPost(id,newPost.title, newPost.author);

        //get post
        Response res = post.getPost(id);
        Assert.assertEquals(res.getStatusCode(), 200);
        Posts p = res.as(Posts.class);

        //verify that new post is added
        Assert.assertEquals(p.title, newPost.title);
        Assert.assertEquals(p.author, newPost.author);
        Assert.assertEquals(String.valueOf(p.id), id.toString());

    }

    @AfterTest
    public void deletePost(){
        //delete the post
        Response res = post.deletePost(id);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @DataProvider(name="verifyAddNewPost")
    public Object[] getDataForAddNewPostBody() {
        Posts post = new Posts();
        post.setId(new Random().nextInt(999));
        post.setTitle("This test is to verify post");
        post.setAuthor("Tester");
        return new Object[]{ post };
    }

}
