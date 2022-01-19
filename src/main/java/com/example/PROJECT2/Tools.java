package com.example.PROJECT2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


public class Tools {

    private static final String DELIMITER_FOR_FILE = "\t";
    private TaxResponse taxResponse;
    private static ArrayList<CountryTax> taxArrayList = new ArrayList<>();
    private String input ;
    String countriesSortedByTaxText;
    String threeBiggestTaxText;
    String threeSmallerTaxText;
    String countriesSortedByTax = "";
    String threeBiggestSorted = "";
    String threeSmallerSorted = "";
   

    public Tools() {
    }
    


    public static int countrySize() {
        return taxArrayList.size();
    }

    public static void setTaxArrayList(ArrayList<CountryTax> taxArrayList) {
        Tools.taxArrayList = taxArrayList;
    }


    //ten String, który użyłem w callApi, przemapuje do tego obiektu poniżej
    public TaxResponse mapToObject(String body) throws JsonProcessingException {
        // ObjectMapper służy do przemapowania obiektów na te Javy obiekty i z powrotem
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        taxResponse = objectMapper.readValue(body, TaxResponse.class);

        System.out.println("Countries: " + taxResponse.getRates().size());
        // przemapowanie obiektu do stringa
        //String out = objectMapper.writeValueAsString(taxResponse);

        return taxResponse; // nowy obiekt zrobić
    }

//    public void exportToFile(String fileName) throws TaxException {
//        try(PrintWriter writer = new PrintWriter(new FileOutputStream(fileName))){
//            for (int i = 0; i < 3; i++) {
//                writer.println("Countries with lowest standard rate: " + taxList.get(i));
//            }
//            writer.println(Main.GAP);
//
//            for (int i = countrySize()-3; i < countrySize() ; i--) {
//                writer.println("Countries with highest standard rate: " + taxList.get(i));
//            }
//        }catch (FileNotFoundException e) {
//            throw new TaxException("File: " + fileName + "is not found" + e.getMessage());
//        }

//    }

    public List<CountryTax> getThreeCountriesWithBiggestStandardRateOfTax() {
        List<CountryTax> threeBiggest = new ArrayList<>();
        //zyskanie 3 krajów z największym rate
        for (int i = countrySize(); i > countrySize() - 3; i--) {
            threeBiggest.add(taxArrayList.get(i - 1));
        }
        return threeBiggest;
    }

    public List<CountryTax> getThreeCountriesWithSmallerStandardRateOfTax() {
        List<CountryTax> threeSmaller = new ArrayList<>();
        //zyskanie 3 krajów z najmniejszym rate
        for (int i = countrySize(); i < 3; i++) {

            threeSmaller.add(taxArrayList.get(i));
        }
        return threeSmaller;
    }

    public List<CountryTax> listSort() {

        taxArrayList = new ArrayList<>(taxResponse.getRates().values());
        //zmieniłem Comparator na comparable bo nie potrzebuje zbadać kilka wartości tylko starczy mi jedna
        Collections.sort(taxArrayList);
        return taxArrayList;
    }

//    public CountryTax getInfoOfCountriesByAbbreviation() {
//        Scanner scanner = new Scanner(System.in);
//        String input;
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
//    public CountryTax getInformationAboutCountryByAbbreviation(){
//
//
//        String writeValue;
//
//        Scanner input = new Scanner(System.in);
//        System.out.println("Write Abbreviation of country for example :cz, Cz");
//        writeValue = input.next();
//
//        countryFound = true;
//    }
    public void CountryTax getInfoOfCountriesByAbbreviation(){
    
        Scanner scanner = new Scanner(System.in);
    

        System.out.println("Write your input of country (or \\\"END\\\" to quit): ");
        while (taxResponse.getRates().get(input) == null) {
        input = scanner.nextLine().toUpperCase();

        if (taxResponse.getRates().get(input) == null) {
            System.out.println("Country not found, please try again.");
            System.out.println(taxResponse.getRates().keySet());
        }
    }
//        return taxResponse.getRates().get(input);
}


}
