package com.desafiosafaty.angelorobson.service;

import com.desafiosafaty.angelorobson.model.GitHubSearchResult;
import com.desafiosafaty.angelorobson.model.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface GitHubService {

  @GET("search/repositories")
  Call<GitHubSearchResult> getRepositories(@Query("q") String query,
                                           @Query("sort") String sortBy,
                                           @Query("page") int start);

  @GET("repos/{login}/{repository}/pulls")
  Call<List<PullRequest>> getPullsRequests(@Path(value = "login", encoded = true) String login,
                                           @Path(value = "repository", encoded = true) String repository,
                                           @Query("page") int page);
}
