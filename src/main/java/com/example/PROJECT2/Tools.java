package com.example.PROJECT2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


public class Tools {

    private static final String DELIMITER_FOR_FILE = "\t";
    private TaxResponse taxResponse ;
    private static ArrayList<CountryTax> taxArrayList;
    private CountryTax countryTax ;
    String countriesSortedByTaxText;
    String threeBiggestTaxText;
    String threeSmallerTaxText;
    String countriesSortedByTax="";
    String threeBiggestSorted = "";
    String threeSmallerSorted = "";
    public boolean countryFound ;

    public Tools() {
    }

    public Tools(boolean countryFound) {
        this.countryFound = countryFound;
    }


    public static int countrySize(){
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

        return taxResponse;
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

            taxArrayList = new ArrayList<>(taxResponse.getRates().values());
            //zmieniłem Comparator na comparable bo nie potrzebuje zbadać kilka wartości tylko starczy mi jedna
            Collections.sort(taxArrayList);
        return taxArrayList;
    }

    public String getInfoOfCountriesByAbbreviation() {
        Scanner scanner = new Scanner(System.in);
        String input;
        taxArrayList = new ArrayList<>();
        boolean countryFound = false ;
        System.out.println("Write your input of country (or \"END\" to quit): ");
        do{
            input = scanner.nextLine();
            if(input.length() !=2) {
                System.err.println("Length of input must be 2 characters");
            } else {
                for(CountryTax countryTax: taxArrayList) {
                    if(input.equalsIgnoreCase(countryTax.country)) {
                        System.out.println(taxArrayList);
                        countryFound=true;

                    }
                }
            }

            if(!countryFound) {
                System.out.println("Country not found, please try again.");
            }

        } while(!input.equalsIgnoreCase("END") && !countryFound);

        return input;
    }


    public CountryTax getCountryTax() {
        return countryTax;
    }
}
