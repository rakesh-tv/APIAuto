import io.restassured.RestAssured;
import io.restassured.builder.*;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.*;
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
        RequestSpecification reqSpec = new RequestSpecBuilder()
                                            .setContentType("application/json")
                                            .setBaseUri(baseURI).build();
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                                            .expectContentType(ContentType.JSON)
                                            .expectStatusCode(201).build();

        User user = new User();
        user.setName(name);
        user.setJob(job);

        User resAsUserClass = given().
                                    spec(reqSpec).
                                    body(user).
                              when().
                                    post("/api/users").
                              then().
                                    spec(resSpec).extract().response().as(User.class);

//        JsonPath jsonRes = new JsonPath(res.asString());
        Assert.assertEquals(resAsUserClass.getName(),"API");
        id = resAsUserClass.getId();
    }

    @Test(dataProvider = "valuesForPutRequestBody")
    public void testPutRequest(String name, String job){

        RestAssured.baseURI = env.get("baseURL").toString();

        RequestSpecification reqSpec = new RequestSpecBuilder()
                                            .setContentType("application/json")
                                            .setBaseUri(baseURI).build();
        ResponseSpecification resSpec = new ResponseSpecBuilder()
                                            .expectContentType(ContentType.JSON)
                                            .expectStatusCode(200).build();

        User user = new User();
        user.setName(name);
        user.setJob(job);

        User resAsUserClass = given().
                                    spec(reqSpec).
                                    body(user).
                              when().
                                    put("/api/users/"+id).
                              then().
                                    spec(resSpec).extract().response().as(User.class);

        Assert.assertEquals(resAsUserClass.getJob(),job);

    }


}
