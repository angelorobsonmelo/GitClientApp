package com.desafiosafaty.angelorobson.contract;

import com.desafiosafaty.angelorobson.model.PullRequest;

import java.util.List;

public interface PullRequestContract
{

    interface View
    {

        void showPullsRequest(List<PullRequest> pullRequests);

        void showErrorMessage();

    }

}
