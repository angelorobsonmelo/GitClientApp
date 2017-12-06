package com.desafiosafaty.angelorobson.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.adapter.PullRequestAdapter;
import com.desafiosafaty.angelorobson.contract.PullRequestContract;
import com.desafiosafaty.angelorobson.listener.EndlessRecyclerOnScrollListener;
import com.desafiosafaty.angelorobson.listener.RecyclerItemClickListener;
import com.desafiosafaty.angelorobson.model.PullRequest;
import com.desafiosafaty.angelorobson.model.Repository;
import com.desafiosafaty.angelorobson.presenter.PullRequestPresenter;
import com.desafiosafaty.angelorobson.service.GitHubService;
import com.desafiosafaty.angelorobson.service.ServiceGenerator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;
import static android.support.v7.widget.RecyclerView.LayoutManager;
import static com.desafiosafaty.angelorobson.constants.Constants.INITIAL_PAGE;
import static com.desafiosafaty.angelorobson.constants.Constants.REPOSITORY;

public class PullRequestFragment extends Fragment implements PullRequestContract.View {

  private Unbinder unbinder;

  @BindView(R.id.recyclerViewPullRequest)
  RecyclerView mRecyclerView;

  @BindView(R.id.progressbarPullRequest)
  ProgressBar mProgressBar;

  LayoutManager mLayoutManager;
  List<PullRequest> pullRequests = new ArrayList<>();
  PullRequestAdapter pullRequestAdapter;
  RepositoryFragment repositoryFragment;
  GitHubService mGitHubService;
  Repository repository;

  PullRequestPresenter pullRequestPresenter;

  public PullRequestFragment() {
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.pull_resquest_fragment, container, false);
    unbinder = ButterKnife.bind(this, view);

    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    setHasOptionsMenu(true);

    mGitHubService = ServiceGenerator.createService(GitHubService.class);

    pullRequestPresenter = new PullRequestPresenter(this, mGitHubService, mProgressBar);

    initConfigurationRecyclerView();

    initEndlessScrollListener();

    initRecyclerItemClickListener();

    return view;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
    callRepositoryFragment();
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onResume () {
    super.onResume();
    repository = getRepository();
    pullRequestPresenter.getPullsRequests(repository.getOwner().getLogin(), repository.getName(), INITIAL_PAGE);
    getActivity().setTitle(repository.getName());
  }

  private void initConfigurationRecyclerView() {
    pullRequestAdapter = new PullRequestAdapter(pullRequests);
    mLayoutManager = new LinearLayoutManager(getContext());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
    mRecyclerView.setAdapter(pullRequestAdapter);
  }

  private void initEndlessScrollListener() {
    mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) mLayoutManager) {
      @Override
      public void onLoadMore(int current_page) {
        pullRequestPresenter.getPullsRequests(repository.getOwner().getLogin(), repository.getName(), current_page);
      }
    });
  }

  private void initRecyclerItemClickListener(){
    mRecyclerView.addOnItemTouchListener(
      new RecyclerItemClickListener(
        getContext(),
        mRecyclerView,
        new RecyclerItemClickListener.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
            openPullRequestInBrowser(pullRequests.get(position).getHtmlUrl());
          }

          @Override
          public void onLongItemClick(View view, int position) {
          }

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          }
        }
      )
    );
  }

  private void openPullRequestInBrowser(String pullRequestUrl) {
    Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequestUrl));
    startActivity(intent);
  }

  private Repository getRepository(){
    Gson gson = new Gson();
    SharedPreferences mPrefs =  getActivity().getPreferences(MODE_PRIVATE);

    String RepositoryJson = mPrefs.getString(REPOSITORY, "");
    return gson.fromJson(RepositoryJson, Repository.class);
  }

  private void callRepositoryFragment() {
    repositoryFragment = new RepositoryFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.frameConteiner, repositoryFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showPullsRequest(List<PullRequest> pullRequestsReturned) {
    pullRequests.addAll(pullRequestsReturned);
    pullRequestAdapter.notifyDataSetChanged();
  }

  @Override
  public void showErrorMessage() {
    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
