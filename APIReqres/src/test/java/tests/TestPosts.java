package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Posts;
import pojo.Profiles;
import requests.Post;
import requests.Profile;
import resources.TestDataForTests;

public class TestPosts extends TestDataForTests {

    Post post = new Post();

    @Test(dataProvider = "verifyAddNewPost")
    public void verifyAddNewProfile(Integer id, String title, String author){
        //add new post
        post.addNewPost(id,title, author);

        //get post
        Response res = post.getPost(id);
        Assert.assertEquals(res.getStatusCode(), 200);
        Posts p = res.as(Posts.class);

        //verify that new post is added
        Assert.assertEquals(p.title, title);
        Assert.assertEquals(p.author, author);
        Assert.assertEquals(String.valueOf(p.id), id.toString());

        //delete the post
        res = post.deletePost(id);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

}
