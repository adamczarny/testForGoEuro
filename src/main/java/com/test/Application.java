package com.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.test.adam.csv.AdamsSimplisticCSVSerializer;
import com.test.domain.Entity;
import com.test.web.HttpHelper;
import java.io.IOException;
import java.util.List;

public class Application {
    public static  String URL = "http://www.goeuro.com/GoEuroAPI/rest/api/v2/position/suggest/en/%S";

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: test.jar [arg]");
            return;
        }
        try {
            List<Entity> entities = HttpHelper.getForObjects(args[0], URL, new TypeReference<List<Entity>>(){});
            System.out.print(AdamsSimplisticCSVSerializer.generateHeader(Entity.class));
            for (Entity entity : entities) {
                System.out.print(AdamsSimplisticCSVSerializer.serialize(entity));
            }
        } catch (Exception ex) {
            System.out.println("Unexpected exception while running program.");
        }
    }


}