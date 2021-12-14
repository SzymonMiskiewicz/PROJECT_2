package com.example.PROJECT2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CallApi {

    public String callApi() throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder().build();//utworzenie klienta

        //utworzenie requestu
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(
                "https://euvatrates.com/rates.json")).GET().build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response status code: " + httpResponse.statusCode() );
        System.out.println("Response body: " + httpResponse.body());

        return httpResponse.body();
    }
}
