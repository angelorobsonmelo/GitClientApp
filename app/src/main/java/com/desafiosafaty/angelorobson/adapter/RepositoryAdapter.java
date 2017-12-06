package com.desafiosafaty.angelorobson.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

  private List<Repository> repositories;

  public RepositoryAdapter(List<Repository> repositories) {
    this.repositories = repositories;
  }

  @Override
  public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemList = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.repository_item_detail, parent, false);

    return new RepositoryViewHolder(itemList);
  }

  @Override
  public void onBindViewHolder(RepositoryViewHolder holder, int position) {
    Repository repository = repositories.get(position);
    holder.textRepository.setText(repository.getName());
    holder.textDescription.setText(repository.getDescription());
    holder.texFork.setText(repository.getForks().toString());
    holder.textStar.setText(repository.getStarsGazers().toString());

    String imgUrl = repository.getOwner().getAvatarUrl();
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
    return repositories.size();
  }

  public class RepositoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageAvatar)
    ImageView imageAvatar;
    @BindView(R.id.textRepository)
    TextView textRepository;
    @BindView(R.id.textDescription)
    TextView textDescription;
    @BindView(R.id.TextFork)
    TextView texFork;
    @BindView(R.id.TextStar)
    TextView textStar;


    public RepositoryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
