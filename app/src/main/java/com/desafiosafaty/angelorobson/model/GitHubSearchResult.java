package com.desafiosafaty.angelorobson.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GitHubSearchResult implements Serializable {

  @SerializedName("total_count")
  private Integer totalCount;

  @SerializedName("incomplete_results")
  private boolean incompleteResults;

  @SerializedName("items")
  private List<Repository> repositories;

  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public boolean isIncompleteResults() {
    return incompleteResults;
  }

  public void setIncompleteResults(boolean incompleteResults) {
    this.incompleteResults = incompleteResults;
  }

  public List<Repository> getRepositories() {
    return repositories;
  }

  public void setRepositories(List<Repository> repositories) {
    this.repositories = repositories;
  }

}
