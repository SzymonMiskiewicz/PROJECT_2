package com.example.PROJECT2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class TaxResponse {

    //musze dodać adnotacje @JsonProperty (value = "")żeby ignorować _ - "belke"
    @JsonProperty(value = "last_updated")
    private String lastUpdated;
    private String disclaimer;
    private Map<String, Object> rates = new HashMap<>();

    public TaxResponse (){}

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Map<String, Object> getRates() {
        return rates;
    }

    public void setRates(Map<String, Object> rates) {
        this.rates = rates;
    }

//    private TaxResponse<String> getKey(CountryTax value){
//        return rates
//                .entrySet()
//                .stream()
//                .filter(country -> CountryTax.equals(country.getValue(), value))
//                .map(Map.Entry::getKey)
//                .findAny();
//    }
//
//    private List<String> getKeys(CountryTax value){
//        return rates
//                .entrySet()
//                .stream()
//                .filter(e -> Object.equals(e.getValue(), value))
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//    }

//    private List<String> getKeys(String value){
//        List<String> keys = new ArrayList<String>();
//        for(String key : rates.keySet()){
//            if(Object.equals(rates.get(key), value)){
//                keys.add(key);
//            }
//        }
//        return keys;
//    }


}
