package tests.jsonMock;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.jsonMock.Comments;
import requests.jsonMock.Comment;

import java.util.Random;

public class TestComments {

    Comment comment = new Comment();
    Integer id;

    @Test(dataProvider = "verifyAddAndEditComment")
    public void verifyAddAndEditComment(Comments newComment){
        //add new comment
        id = newComment.getId();
        Response addCommentRes = comment.addNewComment(id,newComment.getBody(), newComment.getPostId());
        Assert.assertEquals(addCommentRes.getStatusCode(), 201);

        //update comment
        Response updateCommentRes = comment.updateComment(id, "This is updated comment", newComment.getPostId());
        Assert.assertEquals(updateCommentRes.getStatusCode(), 200);

        //verify that comment is updated
        Comments updatedComment = comment.getComment(id);
        Assert.assertEquals(String.valueOf(updatedComment.id), id.toString());
        Assert.assertEquals(updatedComment.body, "This is updated comment");
        Assert.assertEquals(updatedComment.postId, newComment.getPostId());

    }

    @AfterTest
    public void deleteComment(){
        //delete the comment
        Response res = comment.deleteComment(id);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @DataProvider(name="verifyAddAndEditComment")
    public Object[] getDataForAddNewCommentBody() {
        Comments newComment = new Comments();
        newComment.setBody("This is a comment");
        newComment.setId(new Random().nextInt(999));
        newComment.setPostId(1);
        return new Object[]{ newComment};
    }

}
