package utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class RestUtils {
    public static String empName(){
        String empName= RandomStringUtils.randomAlphabetic(1);
        return ("Jhon"+empName);
    }
    public static String empSal(){
        String empSal= RandomStringUtils.randomNumeric(5);
        return (empSal);
    }
    public static String empAge(){
        String empAge= RandomStringUtils.randomNumeric(2);
        return (empAge);
    }
}
