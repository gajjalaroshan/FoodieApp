package com.stackroute.foodieapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationSuggestions {

	@JsonProperty("id")
	private String cityId;
	private String name;

	@JsonProperty("country_id")
	private String countryId;

	@JsonProperty("country_name")
	private String countryName;

	@JsonProperty("country_flag_url")
	private String countryFlagUrl;

	@JsonProperty("should_experiment_with")
	private String shudExpWith;

	@JsonProperty("discovery_enabled")
	private String discoveryEnabled;

	@JsonProperty("has_new_ad_format")
	private String hasNewAdFormat;

	@JsonProperty("is_state")
	private String isState;

	@JsonProperty("state_id")
	private String stateId;

	@JsonProperty("state_name")
	private String stateName;

	@JsonProperty("state_code")
	private String stateCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryFlagUrl() {
		return countryFlagUrl;
	}

	public void setCountryFlagUrl(String countryFlagUrl) {
		this.countryFlagUrl = countryFlagUrl;
	}

	public String getDiscoveryEnabled() {
		return discoveryEnabled;
	}

	public void setDiscoveryEnabled(String discoveryEnabled) {
		this.discoveryEnabled = discoveryEnabled;
	}

	public String getHasNewAdFormat() {
		return hasNewAdFormat;
	}

	public void setHasNewAdFormat(String hasNewAdFormat) {
		this.hasNewAdFormat = hasNewAdFormat;
	}

	public String getIsState() {
		return isState;
	}

	public void setIsState(String isState) {
		this.isState = isState;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCityId() {
		return cityId;
	}

	public void setRestId(String cityId) {
		this.cityId = cityId;
	}

	public String getShudExpWith() {
		return shudExpWith;
	}

	public void setShudExpWith(String shudExpWith) {
		this.shudExpWith = shudExpWith;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}
