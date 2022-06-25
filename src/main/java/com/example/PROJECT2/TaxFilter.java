package com.example.PROJECT2;

import java.util.*;
import java.util.stream.Collectors;

public class TaxFilter {

    CountryTax country = new CountryTax();

    private  String input ;


    public TaxFilter(){}


    public static List <CountryTax> parseObjectToList(TaxResponse tax) {

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

        //zyskanie 3 krajów z najmniejszym rate
        taxList.sort(Comparator.comparing(CountryTax::getStandardRate).reversed());
        List<CountryTax> threeSmallest = taxList.stream()
                .sorted(Comparator.comparing(CountryTax::getStandardRate))
                .collect(Collectors.toList()).subList(0,3);




        return threeSmallest;
    }

    public List<CountryTax> getThreeCountriesWithBiggestStandardRateOfTax(List<CountryTax>taxList) {

        //zyskanie 3 krajów z największym rate
        List<CountryTax> threeBiggest = taxList.stream()
                .sorted(Comparator.comparing(CountryTax::getStandardRate).reversed())
                .collect(Collectors.toList()).subList(0,3);


        return threeBiggest;
    }

    public void getInformationAboutCountryByAbbreviation(List<CountryTax>taxList) {

        System.out.println("Write your input of country (or \"END\" to quit): ");

        Scanner scanner = new Scanner(System.in);

        do {

            input = scanner.nextLine().toUpperCase(Locale.GERMANY);

            Optional<CountryTax> countryYouLookingFor = taxList.stream().filter(countryTax
                    -> Objects.equals(countryTax.getCountryCode(), input)).findAny();
            if (countryYouLookingFor.isPresent()) {
                System.out.println(countryYouLookingFor.get().getCountryCode()
                        + " --->" +
                        "" + countryYouLookingFor.get().getStandardRate());

            } else {
                System.err.println("Length of input must be 2 characters");
            }


        } while (!input.equalsIgnoreCase("END"));


    }


    public String getInformationAboutCountry(List<CountryTax>taxList, String country) {


            Optional<CountryTax> countryYouLookingFor = taxList.stream().filter(countryTax
                    -> Objects.equals(countryTax.getCountryCode(), country)).findAny();
            if (countryYouLookingFor.isPresent()) {
                return countryYouLookingFor.get().getCountryName()
                        + " --->" +
                        "" + countryYouLookingFor.get().getStandardRate();

            } else {
                System.err.println("Length of input must be 2 characters");
            }


        return " Country not found";

    }


}
