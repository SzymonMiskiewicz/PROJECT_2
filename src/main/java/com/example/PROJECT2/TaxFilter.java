package com.example.PROJECT2;

import java.util.*;
import java.util.stream.Collectors;

public class TaxFilter {

    private TaxResponse tax ;
    private List<CountryTax> taxList = new ArrayList<>();
    private String input ;



    public TaxFilter(){}


    public static List <CountryTax> callObject(TaxResponse tax) {

        List<CountryTax> countryTaxList = new ArrayList<>();
        tax.getRates().entrySet().forEach(stringObjectEntry -> {

            Map.Entry<String, Object> entry = stringObjectEntry;

            Map<String, Object> data = (Map<String, Object>) entry.getValue();

            System.out.println(entry.getKey() + " --> " + data.get("standard_rate"));

            CountryTax result = new CountryTax(entry.getKey(), (String) data.get("country"),
                    (Double) data.get("standard_rate"));
            countryTaxList.add(result);


        });
        return countryTaxList;
    }


    public List<CountryTax> getThreeCountriesWithSmallestStandardRateOfTax(List<CountryTax>taxList) {

        taxList.sort(Comparator.comparing(CountryTax::getStandardRate).reversed());
        List<CountryTax> threeSmallest = taxList.stream()
                .sorted(Comparator.comparing(CountryTax::getStandardRate))
                .collect(Collectors.toList()).subList(0,3);


        //zyskanie 3 krajów z najmniejszym rate
//        List<CountryTax> threeSmaller = new ArrayList<>();
//        for (int i = countrySize(); i < 3; i++) {
//
//            threeSmaller.add(taxArrayList.get(i));
//        }

        return threeSmallest;
    }

    public List<CountryTax> getThreeCountriesWithBiggestStandardRateOfTax(List<CountryTax>taxList) {

        List<CountryTax> threeBiggest = taxList.stream()
                .sorted(Comparator.comparing(CountryTax::getStandardRate).reversed())
                .collect(Collectors.toList()).subList(0,3);

//        List<CountryTax> threeBiggest = new ArrayList<>();
//        //zyskanie 3 krajów z największym rate
//        for (int i = countrySize(); i > countrySize() - 3; i--) {
//            threeBiggest.add(taxArrayList.get(i - 1));
//        }
        return threeBiggest;
    }

    public void getInformationAboutCountryByAbbreviation(){

//        System.out.println("Write your input of country (or \"END\" to quit): ");
//        Scanner scanner = new Scanner(System.in);
//
//        while (scanner.hasNext()){
//            String input = scanner.next();
//
//            Optional<CountryTax> countryYouLookingFor = taxList.stream().filter(taxRecord -> Objects.equals(taxRecord.getCountryCode(), input)).findAny();
//            if (countryYouLookingFor.isPresent()){
//                System.out.println(countryYouLookingFor.get().getCountryCode() + " ---" +countryYouLookingFor.get().getStandardRate());
//            }else if (input.length() != 2) {
//                System.err.println("Length of input must be 2 characters");
//            }
//            else {
//                System.out.println("Country not found");
//            }
//        }
        

        System.out.println("Write your input of country (or \"END\" to quit): ");
        Scanner scanner = new Scanner(System.in);

        do {

            input = scanner.nextLine().toUpperCase(Locale.GERMANY);

                Optional<CountryTax> countryYouLookingFor = taxList.stream().filter(countryTax
                        -> Objects.equals(countryTax.getCountryCode(), input)).findAny();
            if(countryYouLookingFor.isPresent()) {
                System.out.println(countryYouLookingFor.get().getCountryCode()
                        + " ---" + countryYouLookingFor.get().getStandardRate());

            } else {
                    System.err.println("Length of input must be 2 characters");
            }


        }while (!input.equalsIgnoreCase("END"));


    }




}
