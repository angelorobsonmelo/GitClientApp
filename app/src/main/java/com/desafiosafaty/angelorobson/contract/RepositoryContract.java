package com.desafiosafaty.angelorobson.contract;

import com.desafiosafaty.angelorobson.model.Repository;

import java.util.List;

public interface RepositoryContract
{

    interface View
    {

        void showRepositories(List<Repository> repositories);

        void showErrorMessage();
    }

}
