package com.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseData {
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("status")
	public String status;
	@JsonProperty("success")
	public String success;
	  @JsonProperty("data")
	  public Data data;


	  @Override
	  public String toString() {
	    return "ResponseData [status=" + status + ", success=" + success + ", data=" + data + "]";
	  }
}