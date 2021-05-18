package resources;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class TestDataForTests {

    @DataProvider(name="verifyAddNewProfile")
    public Object[][] getDataToVerifyAddProfile() {
        return new Object[][]{{"Tester"+String.valueOf(new Random().nextInt(999)), new Random().nextInt(999)}};
    }

    @DataProvider(name="verifyAddNewPost")
    public Object[][] getDataForAddNewPostBody() {
        return new Object[][]{ {new Random().nextInt(999), "This test is to verify post", "Tester"}};
    }

    @DataProvider(name="verifyAddAndEditComment")
    public Object[][] getDataForAddNewCommentBody() {
        return new Object[][]{ {new Random().nextInt(999), "This is a comment", 1}};
    }


}
