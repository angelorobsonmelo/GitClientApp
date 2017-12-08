package com.desafiosafaty.angelorobson.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.fragment.RepositoryFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RepositoryFragment repositoryFragment = new RepositoryFragment();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.frameConteiner, repositoryFragment);
    transaction.commit();
  }
}
