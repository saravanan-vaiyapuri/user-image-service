package com.user.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Image implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("id")
	public String id;
	@JsonProperty("link")
	public String link;
	@JsonProperty("type")
	public String type;
	@JsonProperty("title")
	public String title;
  @JsonProperty("description")
	public String description;
	@JsonProperty("deletehash")
	public String deletehash;
  @Override
  public String toString() {
    return "Image [id=" + id + ", link=" + link + ", type=" + type + ", title=" + title + "]";
  }
	
	
}