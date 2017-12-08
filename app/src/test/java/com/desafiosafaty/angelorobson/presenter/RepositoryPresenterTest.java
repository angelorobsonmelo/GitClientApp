package com.desafiosafaty.angelorobson.presenter;


import com.desafiosafaty.angelorobson.presenter.RepositoryPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.desafiosafaty.angelorobson.constants.Constants.INITIAL_PAGE;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryPresenterTest {

  @Mock
  RepositoryPresenter repositoryPresenter;

  @Test
  public void shouldGetRepositories(){
    repositoryPresenter.getRepositories(INITIAL_PAGE);
  }

}
