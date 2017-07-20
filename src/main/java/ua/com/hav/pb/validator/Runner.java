package ua.com.hav.pb.validator;

import java.util.Map;

/**
 * Created by yulia on 18.07.2017.
 */
public class Runner {
    public static void main(String[] args) {
        Test test = new Test("", "abdecd", "ddd@dcec.co", "ff");
        Map<String, String> map = ReflectionValidator.validate(test);
//        System.out.println("map = " + map);
        map.keySet().forEach(k -> {System.out.println(k + ": " + map.get(k));});
    }
}
