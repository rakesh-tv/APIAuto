package tests.jsonMock;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.jsonMock.Profiles;
import requests.jsonMock.Profile;

import java.util.Arrays;
import java.util.Random;

public class TestProfiles {

    Profile profile = new Profile();
    Integer id;

    @Test(dataProvider = "verifyAddNewProfile")
    public void verifyAddNewProfile(Profiles newProfile){
        //add new profile
        id = newProfile.getId();
        Response res = profile.addNewProfile(newProfile.getName(), id);
        Assert.assertEquals(res.getStatusCode(), 201);

        //get all profiles
        Profiles[] allProfiles = profile.getAllProfiles();

        //verify the new profile is added
        Assert.assertTrue(Arrays.stream(allProfiles).anyMatch(Profiles -> Profiles.getId()==id));

    }

    @AfterTest
    public void deleteProfile(){
        //Delete profile
        Response res = profile.deleteProfile(id);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @DataProvider(name="verifyAddNewProfile")
    public Object[] getDataToVerifyAddProfile() {
        Profiles prof = new Profiles();
        prof.setName("Tester"+String.valueOf(new Random().nextInt(999)));
        prof.setId(new Random().nextInt(999));
        return new Object[]{prof};
    }

}
