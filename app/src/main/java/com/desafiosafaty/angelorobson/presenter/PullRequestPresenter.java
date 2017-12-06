package com.desafiosafaty.angelorobson.presenter;

import android.view.View;
import android.widget.ProgressBar;

import com.desafiosafaty.angelorobson.contract.PullRequestContract;
import com.desafiosafaty.angelorobson.model.PullRequest;
import com.desafiosafaty.angelorobson.service.GitHubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRequestPresenter
{
  private final PullRequestContract.View pullRequestContractView;
  private final GitHubService service;
  private final ProgressBar mProgressBar;

  public PullRequestPresenter(PullRequestContract.View pullRequestContractView, GitHubService service, ProgressBar mProgressBar)
  {
    this.pullRequestContractView = pullRequestContractView;
    this.service = service;
    this.mProgressBar = mProgressBar;
  }

  public void getPullsRequests (String login, final String repository, int page)
  {
    mProgressBar.setVisibility(View.VISIBLE);

    service.getPullsRequests(login, repository, page).enqueue(new Callback<List<PullRequest>>() {
      @Override
      public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
        if (response.isSuccessful()) {
          pullRequestContractView.showPullsRequest(response.body());
          mProgressBar.setVisibility(View.GONE);
        }
      }

      @Override
      public void onFailure(Call<List<PullRequest>> call, Throwable t) {
        mProgressBar.setVisibility(View.GONE);
        pullRequestContractView.showErrorMessage();
      }
    });
  }

}
