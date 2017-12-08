package com.desafiosafaty.angelorobson.activity;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.FrameLayout;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.fragment.RepositoryFragment;

import org.junit.Assert;

import static com.desafiosafaty.angelorobson.constants.Constants.REPOSITORIES;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

  private MainActivity mMainActivity;
  private Instrumentation instrumentation;
  private FrameLayout frameLayout;

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    this.mMainActivity = getActivity();
    this.instrumentation = getInstrumentation();
    this.frameLayout = this.mMainActivity.findViewById(R.id.frameConteiner);
  }

  public void testStartRepositoryFragmentWhenMainActivityIsStarted() throws Exception {
    RepositoryFragment repositoryFragment = new RepositoryFragment();

    mMainActivity.getSupportFragmentManager().beginTransaction().add(frameLayout.getId(), repositoryFragment).commit();

    this.instrumentation.waitForIdleSync();

    View view = repositoryFragment.getView().findViewById(R.id.recyclerViewRepository);

    Assert.assertNotNull(view);
  }

  public void testShowTitle() {
    CharSequence tittle = mMainActivity.getSupportActionBar().getTitle();
    Assert.assertEquals(REPOSITORIES, tittle);
  }

  public void testExistFrameLayout() throws Exception {
    FrameLayout frameLayout = this.mMainActivity.findViewById(R.id.frameConteiner);
    Assert.assertNotNull(frameLayout);
  }


}
