package com.desafiosafaty.angelorobson.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


public class Repository implements Serializable {

  private Integer id;

  private String name;

  @SerializedName("full_name")
  private String fullName;

  private Owner owner;

  @SerializedName("created_at")
  private Date createdAt;

  @SerializedName("html_url")
  private String htmlUrl;

  private String description;

  @SerializedName("stargazers_count")
  private Integer starsGazers;

  @SerializedName("watchers")
  private Integer watchers;

  @SerializedName("forks_count")
  private Integer forks;

  @SerializedName("pulls_url")
  private String pullsUrl;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getStarsGazers() {
    return starsGazers;
  }

  public void setStarsGazers(Integer starsGazers) {
    this.starsGazers = starsGazers;
  }

  public Integer getWatchers() {
    return watchers;
  }

  public void setWatchers(Integer watchers) {
    this.watchers = watchers;
  }

  public Integer getForks() {
    return forks;
  }

  public void setForks(Integer forks) {
    this.forks = forks;
  }

  public String getPullsUrl() {
    return pullsUrl;
  }

  public void setPullsUrl(String pullsUrl) {
    this.pullsUrl = pullsUrl;
  }

}
