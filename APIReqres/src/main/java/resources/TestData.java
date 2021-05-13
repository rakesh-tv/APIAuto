package resources;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name="valuesForGetRequestBody")
    public Object[][] getDataForGetRequestBody() {
        return new Object[][]{{"API", "Testing"}};
    }

    @DataProvider(name="valuesForPutRequestBody")
    public Object[][] getDataForPutRequestBody() {
        return new Object[][]{ {"API", "Learning"}};
    }

}
