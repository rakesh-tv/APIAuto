package requests.jsonMock;

import Utils.Util;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.jsonMock.Profiles;
import tests.jsonMock.TestProfiles;

import static io.restassured.RestAssured.given;

public class Profile {
    private static Logger logger = LogManager.getLogger(TestProfiles.class.getName());

    public Response addNewProfile(String profileName, Integer id) {
        logger.trace("\n------------"+Thread.currentThread().getStackTrace()[1].getMethodName()+"-------------\n");
        RestAssured.baseURI = Util.getBaseURL();

        Profiles newProfile = Profiles.builder().name(profileName).id(id).build();

        Response res  =   given().
                                    contentType("application/json").
                                    body(newProfile).
                                when().
                                    post("/profiles").
                                then().
                                    extract().response();
        logger.info("\n Response:\n"+res.asString());
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
