package com.example.PROJECT2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    private static final String DELIMITER_FOR_FILE = "\t";
    private TaxResponse taxResponse;
    private List<CountryTax>listOfCountries = new ArrayList<>();


    //ten String, który użyłem w callApi, przemapuje do tego obiektu poniżej
    public TaxResponse mapToObject(String body) throws JsonProcessingException {
        // ObjectMapper służy do przemapowania obiektów na te Javy obiekty i z powrotem
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TaxResponse taxResponse = objectMapper.readValue(body, TaxResponse.class);

        System.out.println("Countries: " + taxResponse.getRates().size());
        // przemapowanie obiektu do stringa
        //String out = objectMapper.writeValueAsString(taxResponse);

        return taxResponse;
    }

    public void exportToFile(String fileName) throws TaxException{
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))){
            for( CountryTax countryTax: listOfCountries) {
                writer.println(countryTax.getCountry() + DELIMITER_FOR_FILE +
                        countryTax.getStandardRate() + DELIMITER_FOR_FILE +
                        countryTax.getReducedRate());
            }
        }catch (FileNotFoundException e){
            throw new TaxException("File: "+ fileName+"is not found"+e.getMessage());
        }

    }

    public List<CountryTax> getThreeCountriesWithBiggerStandardRateOfTax(int tax){
        List<CountryTax> threeBigger = new ArrayList<>();
        for (CountryTax countryTax:listOfCountries) {
            for (int i = 0; i < 3; i++) {
                countryTax.getStandardRate( );
            }
            return threeBigger;
        }
    }

    public List<CountryTax> getThreeCountriesWithSmallerStandardRateOfTax(int tax){
        List<CountryTax> threeSmaller = new ArrayList<>();
        for (int i = 0; i <3; i++) {

        } {


        }
        return threeSmaller;
    }

    public int countrySize(){
        return listOfCountries.size();
    }

}
