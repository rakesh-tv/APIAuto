package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Comments;
import pojo.Posts;
import requests.Comment;
import requests.Post;
import resources.TestDataForTests;

public class TestComments extends TestDataForTests {

    Comment comment = new Comment();

    @Test(dataProvider = "verifyAddAndEditComment")
    public void verifyAddAndEditComment(Integer id, String commentText, Integer postId){
        //add new comment
        Response res = comment.addNewComment(id,commentText, postId);
        Assert.assertEquals(res.getStatusCode(), 201);

        //update comment
        res = comment.updateComment(id, "This is updated comment", postId);
        Assert.assertEquals(res.getStatusCode(), 200);

        //verify that comment is updated
        Comments updatedComment = comment.getComment(id);
        Assert.assertEquals(String.valueOf(updatedComment.id), id.toString());
        Assert.assertEquals(updatedComment.body, "This is updated comment");
        Assert.assertEquals(String.valueOf(updatedComment.postId), postId.toString());

        //delete the comment
        res = comment.deleteComment(id);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

}
