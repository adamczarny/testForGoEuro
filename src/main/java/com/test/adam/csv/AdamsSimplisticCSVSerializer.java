package com.test.adam.csv;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.BiFunction;

/**
 * Created by adam on 29.04.15.
 */
public class AdamsSimplisticCSVSerializer {
    public static String serialize(Object object) throws IOException, IllegalAccessException {
        return traverseFieldsWithFunction(object,(x, y) ->
        {
            try {
                return x.get(y) == null ? "" : x.get(y).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Internal logic exception, contact someone.");
            }
        });
    }
    public static String generateHeader(Class clazz) throws IOException, IllegalAccessException {
        try {
            return traverseFieldsWithFunction(clazz.newInstance(),(x, y) ->x.getName());
        } catch (InstantiationException e) {
            throw new RuntimeException("Internal logic exception, contact someone.");
        }
    }

    protected static String traverseFieldsWithFunction(Object object,BiFunction<Field, Object, String> operator) throws IllegalAccessException, IOException {
        StringBuilder builder = new StringBuilder();
        serialize(object, builder, false,operator);
        return builder.append("\n").toString();
    }

    private static void serialize(Object object, Appendable appender, boolean innerCall, BiFunction<Field, Object, String> operator) throws IOException, IllegalAccessException {
        Class<?> targetClass = object.getClass();
        Field[] fields = targetClass.getDeclaredFields();
        String separator = innerCall ? "," : "";
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(object);
            if (isMarkedForSerialization(field)) {
                serialize(value, appender, true, operator);
                continue;
            } else {
                appender.append(separator);
                appender.append(operator.apply(field, object));
            }
            separator = ",";
        }
    }

    private static boolean isMarkedForSerialization(Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof CSVisable) {
                return true;
            }
        }
        return false;
    }
}