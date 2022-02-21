package com.example.PROJECT2;

import java.io.IOException;
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
        TaxFilter taxFilter = new TaxFilter();
        TaxResponse tax = new TaxResponse();
//        CountryTax countryTax = new CountryTax();
        List<CountryTax> countryTaxList ;



        try {
            String body = callApi.callApi();
            tax = tools.mapToObject(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        countryTaxList = taxFilter.callObject(tax);


        //export do pliku
//        try {
//            tools.exportToFile(OUTPUTFILE);
//                } catch (TaxException e) {
//            System.out.println(e.getMessage());
//        }
        // end region

            //wypis wszystkich krajów
        for (int i = 0; i < tools.countriesSize();i++){
            System.out.println(tools.getCountry(i));}
        //end region

        // Three countries with biggest tax
        System.out.println(taxFilter.getThreeCountriesWithBiggestStandardRateOfTax(countryTaxList));
        // end region

        // Three countries with smallest tax
        System.out.println(taxFilter.getThreeCountriesWithSmallestStandardRateOfTax(countryTaxList));
        // end region

        // metoda dla użytkownika
        taxFilter.getInformationAboutCountryByAbbreviation();
        // end region

    }


}
