package com.desafiosafaty.angelorobson.presenter;

import android.view.View;
import android.widget.ProgressBar;

import com.desafiosafaty.angelorobson.contract.RepositoryContract;
import com.desafiosafaty.angelorobson.model.GitHubSearchResult;
import com.desafiosafaty.angelorobson.service.GitHubService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.desafiosafaty.angelorobson.constants.Constants.QUERY;
import static com.desafiosafaty.angelorobson.constants.Constants.SORT_BY;

public class RepositoryPresenter
{
  private final RepositoryContract.View repositoryView;
  private final GitHubService service;
  private final ProgressBar mProgressBar;

  public RepositoryPresenter (RepositoryContract.View repositoryView, GitHubService service, ProgressBar mProgressBar)
  {
    this.repositoryView = repositoryView;
    this.service = service;
    this.mProgressBar = mProgressBar;
  }

  public void getRepositories (int page)
  {
    mProgressBar.setVisibility(View.VISIBLE);

    service.getRepositories(QUERY, SORT_BY, page).enqueue(new Callback<GitHubSearchResult>() {

      @Override
      public void onResponse(Call<GitHubSearchResult> call, Response<GitHubSearchResult> response)
      {
        if (response.isSuccessful()) {
          repositoryView.showRepositories(response.body().getRepositories());
          mProgressBar.setVisibility(View.GONE);
        }
      }

      @Override
      public void onFailure(Call<GitHubSearchResult> call, Throwable t) {
        mProgressBar.setVisibility(View.GONE);
        repositoryView.showErrorMessage();
      }
    });
  }

}
