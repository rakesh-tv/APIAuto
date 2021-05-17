package requests;

import Utils.Util;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pojo.Profiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Profile {

    public Response addNewProfile(String profileName, Integer id) {
        RestAssured.baseURI = Util.getBaseURL();

        Profiles newPost = Profiles.builder().name(profileName).id(id).build();

        Response res  =   given().
                                    contentType("application/json").
                                    body(newPost).
                                when().
                                    post("/profiles").
                                then().
                                    extract().response();
        return res;

    }

    public Profiles[] getAllProfiles(){
        RestAssured.baseURI = Util.getBaseURL();

        Profiles[] allProfiles =   given().
                    contentType("application/json").
                when().
                    get("/profiles").
                then().
                    statusCode(200).extract().response().as(Profiles[].class);

        return allProfiles;

    }

    public Response deleteProfile(Integer profileId){
        RestAssured.baseURI = Util.getBaseURL();

        Response res =   given().
                    contentType("application/json").
                when().
                    delete("/profiles/"+profileId.toString()).
                then().
                    extract().response();
        return res;

    }

}
