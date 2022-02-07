package com.example.PROJECT2;

import java.util.*;

public class TaxFilter {

    private static TaxResponse tax ;
    private static List<CountryTax> taxList = new ArrayList<>();
    private String input ;


    public TaxFilter(){}


    public List<CountryTax> callObject() {
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


    public static List<CountryTax> getThreeCountriesWithSmallerStandardRateOfTax() {
        List<CountryTax> threeSmaller = new ArrayList<>(taxList.subList(0, 3));
        taxList.sort(Comparator.comparing(CountryTax::getStandardRate).reversed());

        //zyskanie 3 krajów z najmniejszym rate
//        List<CountryTax> threeSmaller = new ArrayList<>();
//        for (int i = countrySize(); i < 3; i++) {
//
//            threeSmaller.add(taxArrayList.get(i));
//        }
        return threeSmaller;
    }

    public static List<CountryTax> getThreeCountriesWithBiggestStandardRateOfTax() {

        List<CountryTax> threeBiggest = new ArrayList<>(taxList.subList(0, 3));
        taxList.sort(Comparator.comparing(CountryTax::getStandardRate));
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

            input = scanner.nextLine().toUpperCase(Locale.ROOT);


            Optional<CountryTax> countryYouLookingFor = taxList.stream().filter(countryTax
                    -> Objects.equals(countryTax.getCountryCode(), input)).findAny();

            if (countryYouLookingFor.isPresent()) {

                System.out.println(countryYouLookingFor.get().getCountryCode()
                        + " ---" + countryYouLookingFor.get().getStandardRate());
            } else if (input.length() != 2) {

                System.err.println("Length of input must be 2 characters");

            }else
            {
                System.out.println("Country not found");
            }
        }while (!input.equalsIgnoreCase("END"));

    }



}
