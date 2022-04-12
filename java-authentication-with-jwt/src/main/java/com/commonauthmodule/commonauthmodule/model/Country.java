package com.commonauthmodule.commonauthmodule.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "population",
        "currencies",
        "independent"
})
@Generated("jsonschema2pojo")
public class Country {

    @JsonProperty("name")
    private String name;
    @JsonProperty("population")
    private Integer population;
    @JsonProperty("currencies")
    private List<Currency> currencies = null;
    @JsonProperty("independent")
    private Boolean independent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("population")
    public Integer getPopulation() {
        return population;
    }

    @JsonProperty("population")
    public void setPopulation(Integer population) {
        this.population = population;
    }

    @JsonProperty("currencies")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    @JsonProperty("currencies")
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @JsonProperty("independent")
    public Boolean getIndependent() {
        return independent;
    }

    @JsonProperty("independent")
    public void setIndependent(Boolean independent) {
        this.independent = independent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
