package com.example.PROJECT2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    Tools tools = new Tools();
    public MainController() throws IOException, InterruptedException {
        this.init();
    }

    public void init() throws IOException, InterruptedException {
        CallApi callApi = new CallApi();
        TaxFilter taxFilter = new TaxFilter();
        TaxResponse taxResponse = new TaxResponse();
        String body = callApi.callApi();
        tools.mapToObject(body);
        taxFilter.parseObjectToList(taxResponse);
    }

    @GetMapping (path = "/getThreeCountriesWithSmallestStandardRateOfTax")
    public String getThreeCountriesWithSmallestStandardRateOfTax(List<CountryTax>taxList){
        taxList.sort(Comparator.comparing(CountryTax::getStandardRate).reversed());
        List<CountryTax> threeSmallest = taxList.stream()
                .sorted(Comparator.comparing(CountryTax::getStandardRate))
                .collect(Collectors.toList()).subList(0,3);

        return String.valueOf(threeSmallest);
    }

    @GetMapping (path = "/getThreeCountriesWithBiggestStandardRateOfTax")
    public String getThreeCountriesWithBiggestStandardRateOfTax(List<CountryTax>taxList){
        List<CountryTax> threeBiggest = taxList.stream()
                .sorted(Comparator.comparing(CountryTax::getStandardRate).reversed())
                .collect(Collectors.toList()).subList(0,3);

        return String.valueOf(threeBiggest);
    }

    @GetMapping (path = "/getInformationAboutCountry")
    public String getInformationAboutCountry(List<CountryTax>taxList){
        TaxFilter taxFilter = new TaxFilter();
        return taxFilter.getInformationAboutCountry(taxList);
    }



}
