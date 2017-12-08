package com.desafiosafaty.angelorobson.dao;

import com.desafiosafaty.angelorobson.model.Repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.desafiosafaty.angelorobson.builder.RepositoryBuilder.oneRepository;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryDAOTest {

  private Repository repository;

  @Mock
  private RepositoryDAO repositoryDAO;

  @Before
  public void setUp(){
    repository = oneRepository().builder();
  }

  @Test
  public void shouldSaveRepository() throws Exception {
    repositoryDAO.save(repository);
  }

  @Test
  public void shouldReturnRepository() {
    when(repositoryDAO.getRepository()).thenReturn(repository);
  }

}
