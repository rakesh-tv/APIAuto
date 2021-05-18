package Utils;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Util {

    public static String url;

    public static String getBaseURL() {
        Properties env = new Properties();
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//env.properties");
            env.load(fs);
        }catch (IOException e){
            Assert.fail("Cannot find properties file");
        }
        return env.get("baseURL").toString();
    }


}
