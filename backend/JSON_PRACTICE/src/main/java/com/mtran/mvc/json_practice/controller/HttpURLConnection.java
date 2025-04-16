package com.mtran.mvc.json_practice.controller;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpURLConnection {
    public static void main(String[] args) {
            try{
                URL url= new URL("https://jsonplaceholder.typicode.com/albums");
                java.net.HttpURLConnection httpURLConnection = (java.net.HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                httpURLConnection.disconnect();

                System.out.println(stringBuilder.toString());
                JSONArray jsonArray=(JSONArray) JSONValue.parse(stringBuilder.toString());
                for (Object object : jsonArray) {
                    System.out.println("=====================");
                    JSONObject album = (JSONObject) object;
                    long userId=(long)album.get("userId");
                    long id=(long)album.get("id");
                    String title=(String)album.get("title");
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
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
