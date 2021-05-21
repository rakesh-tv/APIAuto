package requests.heroKUApp;

import Utils.Util;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class auth {

    public static String token = null;

    public static String getAuthToken (){
        RestAssured.baseURI = Util.getBaseURLForHero();


        Response response =   given().
                    contentType("application/json").
                    body(Util.getUsernamePassword()).
                when().
                    post("/auth").
                then().
                    extract().response();

        token = response.jsonPath().get("token");
        return token;

    }

}
