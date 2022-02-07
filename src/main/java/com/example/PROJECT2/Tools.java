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
    TaxFilter taxFilter = new TaxFilter();




    public static int countrySize() {
        return taxArrayList.size();
    }

    //ten String, który użyłem w callApi, przemapuje do tego obiektu poniżej
    public TaxResponse mapToObject(String body) throws JsonProcessingException {
        // ObjectMapper służy do przemapowania obiektów na te Javy obiekty i z powrotem
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         taxResponse = objectMapper.readValue(body, TaxResponse.class);// nevim prot mam to tady spatne

        System.out.println("Countries: " + taxResponse.getRates().size());
        // przemapowanie obiektu do stringa
        String out = objectMapper.writeValueAsString(taxResponse);

        return taxResponse; // nowy obiekt zrobić
    }

   /* public void exportToFile(String fileName) throws TaxException {
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))) {
//            for (int i = 0; i < 3; i++) {
            writer.println("Countries with lowest standard rate: "
                    + taxFilter.getThreeCountriesWithSmallerStandardRateOfTax());
//            }
            writer.println(Main.GAP);

//            for (int i = countrySize()-3; i < countrySize() ; i--) {
//            taxArrayList.get(i)
            writer.println("Countries with highest standard rate: "
                    + taxFilter.getThreeCountriesWithBiggestStandardRateOfTax());
//        }
        }catch (FileNotFoundException e) {
            throw new TaxException("File: " + fileName + "is not found" + e.getMessage());
        }

    }*/


//    public CountryTax getInfoOfCountriesByAbbreviation() {
//
//        boolean countryFound = false;
//        Scanner scanner = new Scanner(System.in);
//
//
//        System.out.println("Write your input of country (or \"END\" to quit): ");
//
//        do {
//            input = scanner.next();
//
//            if (input.length() != 2) {
//                System.err.println("Length of input must be 2 characters");
//            } else {
//                        countryFound = true;
//                for (CountryTax countryTax: taxArrayList) {
//                    if (input.equalsIgnoreCase(String.valueOf(taxResponse.getRates().get(input)))){
//                        System.out.println(taxResponse.getRates().keySet());
//
//                    }
//
//                }
//            }
//
//            if (!countryFound) {
//                System.out.println("Country not found, please try again.");
//            }
//
//        } while (!input.equalsIgnoreCase("END") && !countryFound);
//
//        return taxResponse.getRates().get(input);
//
//    }

//    public  CountryTax getInfoOfCountriesByAbbreviation(){
//
//        Scanner scanner = new Scanner(System.in);
//
//
//        System.out.println("Write your input of country (or \\\"END\\\" to quit): ");
//        while (taxResponse.getRates().get(input) == null) {
//        input = scanner.nextLine().toUpperCase();
//
//        if (taxResponse.getRates().get(input) == null) {
//            System.out.println("Country not found, please try again.");
//            System.out.println(taxResponse.getRates().keySet());
//        }
//    }
//        return taxResponse.getRates().get(input);
//}

    public void exportToFile(String fileName) throws TaxException {
        try {

            PrintWriter writer = new PrintWriter(new FileOutputStream(fileName));
            writer.println("Countries with lowest standard rate: " );
            List<CountryTax> threeSmaller = TaxFilter.getThreeCountriesWithSmallerStandardRateOfTax();

            for(CountryTax tax : threeSmaller) {

                writer.println(tax );
            }

            writer.println(Main.GAP);

            writer.println("Countries with highest standard rate: ");
            List <CountryTax> threeBiggest = TaxFilter.getThreeCountriesWithBiggestStandardRateOfTax();

            for (CountryTax tax : threeBiggest) {
                writer.println(tax);
            }
        } catch(FileNotFoundException e) {
            throw new TaxException("File: " + fileName + "is not found" + e.getMessage());
        }
    }

}
