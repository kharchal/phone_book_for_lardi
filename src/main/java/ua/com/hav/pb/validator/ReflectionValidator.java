package ua.com.hav.pb.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunny on 18.07.2017.
 */
public class ReflectionValidator {

    public static Map<String, String> validate(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
        Map<String, String> errors = new HashMap<>();
        Class clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object value = field.get(object);
                if (field.getType().equals(String.class)) {
                    String str = (String) value;
                    if (field.isAnnotationPresent(Null.class)) {
                        if (str == null || str.equals("")) {
                            continue;
                        }
                    }
                    if (field.isAnnotationPresent(Size.class)) {
                        Size annotation = field.getAnnotation(Size.class);
                        int min = annotation.min();
                        int max = annotation.max();
                        String msg = annotation.message();
                        if (str.length() < min || str.length() > max) {
                            errors.put(name, msg);
                        }
                    }
                    if (field.isAnnotationPresent(Pattern.class)) {
                        Pattern annotation = field.getAnnotation(Pattern.class);
                        String regexp = annotation.regexp();
                        String msg = annotation.message();
                        if (!str.matches(regexp)) {
                            errors.put(name, msg);
                        }
                    }
                    if (field.isAnnotationPresent(NotNull.class)) {
                        NotNull annotation = field.getAnnotation(NotNull.class);
                        String msg = annotation.message();
                        if (str == null || str.equals("")) {
                            errors.put(name, msg);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return errors;
    }
}
