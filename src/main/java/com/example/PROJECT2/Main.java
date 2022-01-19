package com.example.PROJECT2;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final String OUTPUTFILE = "3SmallAnd3BigRates.txt";
    public static final String GAP = "\n=========================================";
    private static CountryTax CountryTax;
    public static ArrayList<CountryTax> taxList = new ArrayList<>();
    private static Object ArrayList;


    public static void main(String[] args) {
        // wytworze sobie instancje klasy CallApi żeby zawołać metode callApi
        CallApi callApi = new CallApi();
        //wytworze instancje klasy Tools do mapowania na obiekt
        Tools tools = new Tools();


        try {
           String body = callApi.callApi();
           tools.mapToObject(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        //export do pliku
//        try {
//            tools.exportToFile(OUTPUTFILE);
//                } catch (TaxException e) {
//            System.err.println(e.getMessage());
//        }
        // end region

            //test
//        System.out.println(tools.listSort());
            System.out.println(tools.getInfoOfCountriesByAbbreviation());

    }


}
