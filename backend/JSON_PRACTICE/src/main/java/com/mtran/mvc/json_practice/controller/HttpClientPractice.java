package com.mtran.mvc.json_practice.controller;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientPractice {
    public static void main(String[] args)  {
        try{
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest=HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/albums"))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JSONArray jsonArray=(JSONArray) JSONValue.parse(response.body());
             for( Object o:jsonArray){
                 JSONObject album = (JSONObject) o;
                 long userId=(long)album.get("userId");
                 long id=(long)album.get("id");
                 String title=(String)album.get("title");
                 System.out.println("=====================");
                 System.out.println("User ID: "+ userId);
                 System.out.println("ID: "+ id);
                 System.out.println("Title: "+ title);

             }
            FileWriter fileWriter= new FileWriter("D:\\EVOTEK_PRACTICE\\tuan1\\Tuan1_Json_API\\album.txt");
            int index = 1;
            fileWriter.write("UserID-ID-NAME TITLE"+ "\n");
            for (Object object : jsonArray) {
                JSONObject album = (JSONObject) object;
                String title = (String) album.get("title");
                long userId=(long)album.get("userId");
                long id=(long)album.get("id");
                fileWriter.write(userId + "-"+id+"-" + title + "\n");
                index++;
            }
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
