package com.desafiosafaty.angelorobson.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.model.PullRequest;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {

  private List<PullRequest> pullRequests;

  public PullRequestAdapter(List<PullRequest> pullRequests) {
    this.pullRequests = pullRequests;
  }

  @Override
  public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemList = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.pull_request_item_detail, parent, false);
    return new PullRequestViewHolder(itemList);
  }

  @Override
  public void onBindViewHolder(PullRequestViewHolder holder, int position) {
    PullRequest pullRequest = pullRequests.get(position);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss");
    String createAt = simpleDateFormat.format(pullRequest.getCreatedAt());

    holder.textTitle.setText(pullRequest.getTitle());
    holder.textDataCreated.setText("Created at " + createAt);
    holder.textBody.setText(pullRequest.getBody());

    String imgUrl = pullRequest.getUser().getAvatarUrl();
    if (imgUrl != null && !imgUrl.equals("")) {
      Picasso.with(holder.imageAvatar.getContext())
        .load(imgUrl)
        .error(R.drawable.ic_github_placeholder)
        .placeholder(R.drawable.ic_github_placeholder)
        .into(holder.imageAvatar);
    }
  }

  @Override
  public int getItemCount() {
    return pullRequests.size();
  }

  public class PullRequestViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageAvatar)
    ImageView imageAvatar;
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDataCreated)
    TextView textDataCreated;
    @BindView(R.id.textBody)
    TextView textBody;

    public PullRequestViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
