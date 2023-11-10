package com.cydeo.lab08rest.dto;

import java.util.LinkedHashMap;
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
        "success",
        "terms",
        "privacy",
        "timestamp",
        "source",
        "quotes"
})
@Generated("jsonschema2pojo")
public class CurrencyDTO {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("terms")
    private String terms;
    @JsonProperty("privacy")
    private String privacy;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("source")
    private String source;
    @JsonProperty("quotes")
    private Quotes quotes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("terms")
    public String getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(String terms) {
        this.terms = terms;
    }

    @JsonProperty("privacy")
    public String getPrivacy() {
        return privacy;
    }

    @JsonProperty("privacy")
    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("quotes")
    public Quotes getQuotes() {
        return quotes;
    }

    @JsonProperty("quotes")
    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
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