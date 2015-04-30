package com.test.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.domain.Entity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by adam on 30.04.15.
 */
public class HttpHelper {
    public static <T> T getForObjects(String query, String endpoint,TypeReference<T> typeReference) throws IOException {
        String responseBody = getResponseBodyFor(query,endpoint);
        return getEntitiesFrom(responseBody, typeReference);
    }

    private static <T> T getEntitiesFrom(String responseBody,TypeReference<T> typeReference) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseBody, typeReference);
    }

    private static String getResponseBodyFor(String query,String endpoint) throws IOException {
        HttpClient client = new DefaultHttpClient();
        String url = String.format(endpoint, URLEncoder.encode(query), "UTF-8");
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
