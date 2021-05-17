package resources;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class TestDataForRequests {

    @DataProvider(name="valuesForGetRequestBody")
    public Object[][] getDataForGetRequestBody() {
        return new Object[][]{{"API", "Testing"}};
    }

    @DataProvider(name="valuesForPutRequestBody")
    public Object[][] getDataForPutRequestBody() {
        return new Object[][]{ {"API", "Learning"}};
    }

    @DataProvider(name="valuesForAddNewPostRequestBody")
    public Object[][] getDataForAddNewPostBody() {
        return new Object[][]{ {new Random().nextInt(999), "This is a post", "Tester"}};
    }

    @DataProvider(name="valuesForAddNewCommentRequestBody")
    public Object[][] getDataForAddNewCommentBody() {
        return new Object[][]{ {1, "This is a comment", 1}};
    }

    @DataProvider(name="valuesForAddNewProfileRequestBody")
    public Object[] getDataForAddNewProfileBody() {
        return new Object[][]{{ "Tester",new Random().nextInt(999)} };
    }

    @DataProvider(name="valuesForUpdateComment")
    public Object[][] getDataForUpdateComment() {
        return new Object[][]{ {1} };
    }

}
