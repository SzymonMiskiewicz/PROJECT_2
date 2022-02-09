package com.example.PROJECT2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.example.PROJECT2.Tools.countrySize;

public class Main {

    private static final String OUTPUTFILE = "3SmallAnd3BigRates.txt";
    public static final String GAP = "\n=========================================";





    public static void main(String[] args) {
        // wytworze sobie instancje klasy CallApi żeby zawołać metode callApi
        CallApi callApi = new CallApi();
        //wytworze instancje klasy Tools do mapowania na obiekt
        Tools tools = new Tools();
        TaxFilter taxFilter = new TaxFilter();
        TaxResponse tax = new TaxResponse();


        try {
            String body = callApi.callApi();
            tax = tools.mapToObject(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        taxFilter.callObject(tax);


        //export do pliku
//        try {
//            tools.exportToFile(OUTPUTFILE);
//                } catch (TaxException e) {
//            System.out.println(e.getMessage());
//        }
        // end region

            //wypis wszystkich krajów
        for (int i = 0; i < countrySize();i++){
            System.out.println(countrySize());}
        //end region

        // Three countries with bigger tax
        //System.out.println(taxFilter.getThreeCountriesWithBiggestStandardRateOfTax());
        // end region

        // Three countries with smaller tax
       // System.out.println(taxFilter.getThreeCountriesWithSmallerStandardRateOfTax());
        // end region

        // metoda dla użytkownika
        taxFilter.getInformationAboutCountryByAbbreviation();
        // end region

    }


}
