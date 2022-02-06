package com.example.PROJECT2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CountryTax extends ArrayList<TaxResponse> {

    String countryCode;
    @JsonProperty (value = "country")
    String countryName;
    @JsonProperty (value = "standard_rate")
    Double standardRate;

    public CountryTax(){
    }

    public CountryTax (String countryCode , String countryName, Double standardRate){
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.standardRate = standardRate;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(Double standardRate) {
        this.standardRate = standardRate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CountryTax{");
        sb.append("country:'").append(countryCode).append('\'');
        sb.append(", standardRate:").append(standardRate);
        sb.append(", reducedRate:").append(countryName);
        sb.append('}');
        return sb.toString();
    }


}
