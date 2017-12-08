package com.desafiosafaty.angelorobson;


import com.desafiosafaty.angelorobson.activity.MainActivityTest;
import com.desafiosafaty.angelorobson.fragment.PullRequestFragmentTest;
import com.desafiosafaty.angelorobson.fragment.RepositoryFragmentTest;

import org.junit.runner.RunWith;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses(
  {
    MainActivityTest.class,
    RepositoryFragmentTest.class,
    PullRequestFragmentTest.class
  })
public class SuiteUserTest {}
