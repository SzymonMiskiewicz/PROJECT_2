package com.example.PROJECT2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Tools {

    private static final String DELIMITER_FOR_FILE = "\t";
    private TaxResponse taxResponse;
    private List<CountryTax> taxList;
    CountryTax countryTax;
    public int countrySize(){
        return taxList.size();
    }



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

    public void exportToFile(String fileName) throws TaxException {
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))){
            for (int i = 0; i < 3; i++) {
                writer.println("Countries with lowest standard rate: " + taxList.get(i));
            }
            writer.println(Main.GAP);

            for (int i = countrySize()-3; i < countrySize() ; i--) {
                writer.println("Countries with highest standard rate: " + taxList.get(i));
            }
        }catch (FileNotFoundException e) {
            throw new TaxException("File: " + fileName + "is not found" + e.getMessage());
        }

    }

    public List<CountryTax> getThreeCountriesWithBiggestStandardRateOfTax(List<CountryTax> countriesSortedByTax){
        List<CountryTax> threeBiggest = new ArrayList<>();
        //zyskanie 3 krajów z największym rate
        for (int i = countriesSortedByTax.size(); i > countriesSortedByTax.size() -3; i--) {
            threeBiggest.add(countriesSortedByTax.get(i-1));
        }
        return threeBiggest;
    }

    public List<CountryTax> getThreeCountriesWithSmallerStandardRateOfTax(List<CountryTax>countriesSortedByTax){
        List<CountryTax> threeSmaller = new ArrayList<>();
        //zyskanie 3 krajów z najmniejszym rate
        for (int i = countriesSortedByTax.size(); i <3; i++) {

            threeSmaller.add(countriesSortedByTax.get(i));
        }
        return threeSmaller;
    }

    public List<CountryTax> listSort()  {

            taxList = new ArrayList<>(taxResponse.getRates().values());
            //zmieniłem Comparator na comparable bo nie potrzebuje zbadać kilka wartości tylko starczy mi jedna
            Collections.sort(taxList);
        return taxList;
    }

    public static void getInfoOfCountriesByAbbreviation (List<CountryTax> allCountryTax) {
        Scanner scanner = new Scanner(System.in);
        String abbreviation;
        boolean countryFound=false;
        System.out.println("Write your abbreviation of country (or \"END\" to quit): ");
        do{
            abbreviation = scanner.nextLine();
            if(abbreviation.length() !=2) {
                System.out.println("Length of abbreviation must be 2 characters");
            } else {
                for(CountryTax countryTax:allCountryTax) {
                    if(abbreviation.equalsIgnoreCase(countryTax.country)) {
                        System.out.println(countryTax);
                        countryFound=true;
                        break;
                    }
                }
            }

            if(!countryFound) {
                System.out.println("Country not found, please try again.");
            }
        } while(!abbreviation.equalsIgnoreCase("END") && !countryFound);
    }

//    public static void countryByAbbreviation(String country, CountryTax standardRate){
//        Scanner scanner = new Scanner(System.in);
//        String abbrev = "";
//        Map<String, CountryTax> abbreviation = new TreeMap<>();
//        for (CountryTax countryTax:listOfCountries) {
//            System.out.println("Write your abbreviation of country (or \"END\" to quit): ");
//            do {
//                abbrev = scanner.nextLine();
//            if(abbrev.length() !=2) {
//                System.out.println("Length of abbreviation must be 2 characters");
//            CountryTax.put(country,standardRate);
//
//            }
//            }while (!abbrev.equalsIgnoreCase("END"));
//        }
//
//    }




}
