package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Profiles;
import requests.Profile;
import resources.TestDataForTests;

public class TestProfiles extends TestDataForTests {

    Profile profile = new Profile();

    @Test(dataProvider = "verifyAddNewProfile")
    public void verifyAddNewProfile(String profileName, Integer id){
        //add new profile
        Response res = profile.addNewProfile(profileName, id);
        Assert.assertEquals(res.getStatusCode(), 201);

        //get all profiles
        Profiles[] allProfiles = profile.getAllProfiles();

        //verify the new profile is added
        Boolean newProfileAdded = false;
        for(Profiles prof : allProfiles){
          if(prof.id==id){
              Assert.assertEquals(prof.name, profileName);
              newProfileAdded = true;
              break;
          }
        }
        Assert.assertTrue(newProfileAdded);

        //Delete profile
        res = profile.deleteProfile(id);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

}
