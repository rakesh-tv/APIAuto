import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.User;
import resources.TestData;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.*;

public class Basic extends TestData {
    Properties env = new Properties();
    String id = "";

    @BeforeClass
    public void setup() throws IOException {
        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        env.load(fs);
    }

    @Test(dataProvider = "valuesForGetRequestBody")
    public void testGetRequest(String name, String job){
        RestAssured.baseURI = env.get("baseURL").toString();

        User user = new User();
        user.setName(name);
        user.setJob(job);

        User resAsUserClass =   given().
                                    contentType("application/json").
                                    body(user).
                                when().
                                    post("/api/users").
                                    then().
                                statusCode(201).extract().response().as(User.class);

        Assert.assertEquals(resAsUserClass.getName(),"API");
        id = resAsUserClass.getId();
    }

    @Test(dataProvider = "valuesForPutRequestBody")
    public void testPutRequest(String name, String job){

        RestAssured.baseURI = env.get("baseURL").toString();

        User user = new User();
        user.setName(name);
        user.setJob(job);

        User resAsUserClass =   given().
                                    contentType("application/json").
                                    body(user).
                                when().
                                    put("/api/users/"+id).
                                then().
                                    statusCode(200).extract().response().as(User.class);

        Assert.assertEquals(resAsUserClass.getJob(),job);

    }


}
