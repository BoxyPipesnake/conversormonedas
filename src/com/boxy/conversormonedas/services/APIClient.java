package com.boxy.conversormonedas.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIClient {
    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    public static String fetchDataFromAPI(String url) {
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();


            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful (status code 200)
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Error: HTTP request failed with status code " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: Failed to fetch data from API: " + e.getMessage());
            return null;
        }
    }

}

