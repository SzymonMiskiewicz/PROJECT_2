package com.example.PROJECT2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String OUTPUTFILE = "3SmallAnd3BigRates.txt";
    public static final String GAP = "\n=========================================";


    public static void main(String[] args) {
        // wytworze sobie instancje klasy CallApi żeby zawołać metode callApi
        CallApi callApi = new CallApi();
        //wytworze instancje klasy Tools do mapowania na obiekt
        Tools tools = new Tools();
        CountryTax countryTax = new CountryTax();

        try {
           String body= callApi.callApi();
           tools.mapToObject(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        //export do pliku
        try {
            tools.exportToFile(OUTPUTFILE);
                } catch (TaxException e) {
            System.err.println(e.getMessage());
        }
        // end region


            Tools.getInfoOfCountriesByAbbreviation(tools.listSort());

    }


}
