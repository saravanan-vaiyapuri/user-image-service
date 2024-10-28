package com.user.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseUserData {
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("status")
	public String status;
	@JsonProperty("success")
	public String success;
	  @JsonProperty("user")
	  public User user;
	  @JsonProperty("images")
	  public ArrayList<Image> images;

	  @Override
	  public String toString() {
	    return "ResponseData [status=" + status + ", success=" + success + ", user=" + user.getEmail() + ", images="+images+"]";
	  }
}