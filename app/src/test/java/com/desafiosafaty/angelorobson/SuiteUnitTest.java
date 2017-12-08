package com.desafiosafaty.angelorobson;

import com.desafiosafaty.angelorobson.dao.RepositoryDAOTest;
import com.desafiosafaty.angelorobson.presenter.PullRequestPresenterTest;
import com.desafiosafaty.angelorobson.presenter.RepositoryPresenterTest;

import org.junit.runner.RunWith;


@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses(
  {
    RepositoryDAOTest.class,
    PullRequestPresenterTest.class,
    RepositoryPresenterTest.class
  })
public class SuiteUnitTest {}
