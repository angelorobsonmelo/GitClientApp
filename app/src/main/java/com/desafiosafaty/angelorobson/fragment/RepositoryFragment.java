package com.desafiosafaty.angelorobson.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.adapter.RepositoryAdapter;
import com.desafiosafaty.angelorobson.contract.RepositoryContract;
import com.desafiosafaty.angelorobson.dao.RepositoryDAO;
import com.desafiosafaty.angelorobson.listener.EndlessRecyclerOnScrollListener;
import com.desafiosafaty.angelorobson.listener.RecyclerItemClickListener;
import com.desafiosafaty.angelorobson.model.Repository;
import com.desafiosafaty.angelorobson.presenter.RepositoryPresenter;
import com.desafiosafaty.angelorobson.service.GitHubService;
import com.desafiosafaty.angelorobson.service.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.widget.RecyclerView.LayoutManager;
import static com.desafiosafaty.angelorobson.constants.Constants.INITIAL_PAGE;
import static com.desafiosafaty.angelorobson.constants.Constants.REPOSITORIES;

public class RepositoryFragment extends Fragment implements RepositoryContract.View {

  private Unbinder unbinder;

  RepositoryAdapter repositoryAdapter;

  LayoutManager mLayoutManager;

  @BindView(R.id.recyclerViewRepository)
  RecyclerView mRecyclerView;

  @BindView(R.id.progressbar)
  ProgressBar mProgressBar;

  GitHubService mGitHubService;

  List<Repository> repositories = new ArrayList<>();

  PullRequestFragment pullRequestFragment;

  RepositoryPresenter repositoryPresenter;

  RepositoryDAO repositoryDAO;

  public RepositoryFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.repository_fragment, container, false);
    unbinder = ButterKnife.bind(this, view);

    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    mGitHubService = ServiceGenerator.createService(GitHubService.class);

    repositoryPresenter = new RepositoryPresenter(this, mGitHubService, mProgressBar);

    repositoryDAO = new RepositoryDAO(getActivity());

    getActivity().setTitle(REPOSITORIES);

    initConfigurationRecyclerView();

    initEndlessScrollListener();

    initRecyclerItemClickListener();

    return view;
  }

  @Override
  public void onResume () {
    super.onResume();
    repositoryPresenter.getRepositories(INITIAL_PAGE);
  }

  private void initConfigurationRecyclerView() {
    repositoryAdapter = new RepositoryAdapter(repositories);
    mLayoutManager = new LinearLayoutManager(getContext());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
    mRecyclerView.setAdapter(repositoryAdapter);
  }

  private void initEndlessScrollListener() {
    mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) mLayoutManager) {
      @Override
      public void onLoadMore(int currentPage) {
        repositoryPresenter.getRepositories(currentPage);
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
            repositoryDAO.save(repositories.get(position));
            callFragmentPullRequest();
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

  private void callFragmentPullRequest() {
    pullRequestFragment = new PullRequestFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.frameConteiner, pullRequestFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void showRepositories(List<Repository> repositoriesReturned) {
    repositories.addAll(repositoriesReturned);
    repositoryAdapter.notifyDataSetChanged();
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
