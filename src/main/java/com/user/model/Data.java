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
public class Data {
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("id")
	public String id;
	@JsonProperty("link")
	public String link;
	@JsonProperty("type")
	public String type;
	@JsonProperty("title")
	public String title;
	@JsonProperty("images")
	public ArrayList<Image> images;
	@JsonProperty("description")
	public String description;
	  @Override
	  public String toString() {
	    return "Data [id=" + id + ", link=" + link + ", type=" + type + ", title=" + title + "]";
	  }
}