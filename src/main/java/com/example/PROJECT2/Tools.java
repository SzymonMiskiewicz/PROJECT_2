package com.example.PROJECT2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Tools {

    private static final String DELIMITER_FOR_FILE = "\t";
    private TaxResponse taxResponse;
    private static List<CountryTax> taxArrayList = new ArrayList<>();
    private TaxFilter taxFilter = new TaxFilter();



    public int countriesSize (){
        return taxArrayList.size();
    }

    public CountryTax getCountry(int index){
        return taxArrayList.get(index);
    }

    //ten String, który użyłem w callApi, przemapuje do tego obiektu poniżej
    public TaxResponse mapToObject(String body) throws JsonProcessingException {
        // ObjectMapper służy do przemapowania obiektów na te Javy obiekty i z powrotem
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         taxResponse = objectMapper.readValue(body, TaxResponse.class);

        System.out.println("Countries: " + taxResponse.getRates().size());
        // przemapowanie obiektu do stringa
        String out = objectMapper.writeValueAsString(taxResponse);

        return taxResponse;
    }

    public void exportToFile(String fileName, List<CountryTax>taxList) throws TaxException {
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))) {
//            for (int i = 0; i < 3; i++) {
            writer.println("Countries with lowest standard rate: \n\n"
                    + taxFilter.getThreeCountriesWithSmallestStandardRateOfTax(taxList));
//            }
            writer.println(Main.GAP);
//brakuje FORMAT
//            for (int i = countrySize()-3; i < countrySize() ; i--) {
//            taxArrayList.get(i)
            writer.println("Countries with highest standard rate: \n\n"
                    + taxFilter.getThreeCountriesWithBiggestStandardRateOfTax(taxList));
//        }
        }catch (FileNotFoundException e) {
            throw new TaxException("File: " + fileName + "is not found" + e.getMessage());
        }

    }

    public static String formatThreeCountriesWithBiggestStandardRateOfTax(List<CountryTax>taxArrayList, TaxFilter taxFilter) {

//        TaxFilter taxFilter = new TaxFilter();

        StringBuilder builder = new StringBuilder(
                "List of Three countries with Biggest Standard rate of tax: \n"

                        + taxFilter.getThreeCountriesWithBiggestStandardRateOfTax(taxArrayList)
                        + System.lineSeparator());

        return builder.toString();
    }

    public static String formatThreeCountriesWithSmallestStandardRateOfTax(List<CountryTax>taxArrayList, TaxFilter taxFilter) {

        StringBuilder builder = new StringBuilder(
                "List of Three countries with Smallest Standard rate of tax: \n"

                        + taxFilter.getThreeCountriesWithSmallestStandardRateOfTax(taxArrayList)
                        + System.lineSeparator());

        return builder.toString();
    }






}
