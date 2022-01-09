package com.example.PROJECT2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryTax implements Comparable <CountryTax> {

    String country;
    @JsonProperty(value = "standard_rate")
    Double standardRate;
    @JsonProperty(value = "reduced_rate")
    String reducedRate;

    public CountryTax(){
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(Double standardRate) {
        this.standardRate = standardRate;
    }

    public String getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(String reducedRate) {
        this.reducedRate = reducedRate;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CountryTax{");
        sb.append("country:'").append(country).append('\'');
        sb.append(", standardRate:").append(standardRate);
        sb.append(", reducedRate:").append(reducedRate);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int compareTo(CountryTax countryTax) {
        return (int) (this.standardRate - countryTax.getStandardRate());
    }
}
