package org.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URL;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {

        try {
            // Create a URL object with the Chuck Norris Jokes API URL
            URL apiUrl = new URL("https://api.zippopotam.us/us/33162");
            // Open a connection to the API URL
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            // Set the HTTP request method to GET
            connection.setRequestMethod("GET");
            // Get the response code from the API
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the API response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the reader and connection
                reader.close();
                connection.disconnect();
                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                // Save the JSON response to a file
                try (FileWriter fileWriter = new FileWriter("zippopotam.json")) {
                    fileWriter.write(jsonResponse.toString(4)); // Use 4 for pretty-printing
                    System.out.println(jsonResponse.toString(4));
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Error: Unable to fetch api.zippopotam.us Response code: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}