package Utils;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Util {

    static Properties env = new Properties();

    public static void readPropertiesFile(){
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//env.properties");
            env.load(fs);
        }catch (IOException e){
            Assert.fail("Cannot find properties file");
        }
    }

    public static String getBaseURL() {
        readPropertiesFile();
        return env.get("baseURL").toString();
    }

    public static String getBaseURLForHero() {
        readPropertiesFile();
        return env.get("baserURL_hero").toString();
    }

    public static String getUsernamePassword(){
        readPropertiesFile();
        return "{\"username\":\""+env.get("userName").toString()+"\",\"password\":\""+env.get("password").toString()+"\"}";
    }


}
