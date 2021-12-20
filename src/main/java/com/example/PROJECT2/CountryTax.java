package com.example.PROJECT2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryTax {

    String country;
    @JsonProperty(value = "standard_rate")
    double standardRate;
    @JsonProperty(value = "reduced_rate")
    String reducedRate;
    String abbreviation;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(double standardRate) {
        this.standardRate = standardRate;
    }

    public String getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(String reducedRate) {
        this.reducedRate = reducedRate;
    }

    public String getAbbreviation() {
        return abbreviation;
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


}
