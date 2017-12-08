package com.desafiosafaty.angelorobson.builder;


import com.desafiosafaty.angelorobson.model.Repository;

import static com.desafiosafaty.angelorobson.constants.Constants.LOGIN_TEST;
import static com.desafiosafaty.angelorobson.constants.Constants.REPOSITORY_NAME_TEST;

public class RepositoryBuilder {

  private Repository repository;

  public RepositoryBuilder() {
  }

  public static RepositoryBuilder oneRepository(){
    RepositoryBuilder builder = new RepositoryBuilder();
    builder.repository = new Repository();
    builder.repository.getOwner().setLogin(LOGIN_TEST);
    builder.repository.setName(REPOSITORY_NAME_TEST);
    return builder;
  }

  public Repository builder(){
    return repository;
  }
}
