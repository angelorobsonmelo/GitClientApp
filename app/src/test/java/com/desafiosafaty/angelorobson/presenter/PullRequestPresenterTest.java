package com.desafiosafaty.angelorobson.presenter;


import com.desafiosafaty.angelorobson.builder.RepositoryBuilder;
import com.desafiosafaty.angelorobson.model.Repository;
import com.desafiosafaty.angelorobson.presenter.PullRequestPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.desafiosafaty.angelorobson.builder.RepositoryBuilder.*;
import static com.desafiosafaty.angelorobson.constants.Constants.INITIAL_PAGE;

@RunWith(MockitoJUnitRunner.class)
public class PullRequestPresenterTest {


  @Mock
  PullRequestPresenter pullRequestPresenter;

  Repository repository;

  @Before
  public void setup() {
    repository = oneRepository().builder();
  }

  @Test
  public void shouldGetRepositories(){
    pullRequestPresenter.getPullsRequests(repository.getOwner().getLogin(), repository.getName(), INITIAL_PAGE);
  }


}
