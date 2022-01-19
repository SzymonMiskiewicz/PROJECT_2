package com.example.PROJECT2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TaxResponse {

    //musze dodać adnotacje @JsonProperty (value = "")żeby ignorować _ - "belke"
    @JsonProperty(value = "last_updated")
    private String lastUpdated;
    private String disclaimer;
    private Map<String, CountryTax> rates = new TreeMap<>();

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

    public Map<String, CountryTax> getRates() {
        return rates;
    }

    public void setRates(Map<String, CountryTax> rates) {
        this.rates = rates;
    }
}
