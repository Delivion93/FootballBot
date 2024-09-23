package com.Delivion.matchInfo;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Client {
    private static final String URL = "http://livescore-api.com/api-client/matches/live.json?key=0XEdnqAmPV1TVEzu&secret=E6fPzYjnaiyF31U4ZyhnZSJONKHIphd4";

    public static MatchInfo sendGet() throws IOException {

        MatchInfo matchInfo =new MatchInfo();
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);


        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine=in.readLine())!=null){
                response.append(inputLine);
            }
            in.close();
            matchInfo =new Gson().fromJson(response.toString(), MatchInfo.class);

        }
        else {
            System.out.println("GET request not worked");
        }
        return matchInfo;
    }

}
