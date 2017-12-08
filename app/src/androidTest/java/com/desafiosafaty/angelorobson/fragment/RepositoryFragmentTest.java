package com.desafiosafaty.angelorobson.fragment;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.support.test.annotation.UiThreadTest;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.activity.MainActivity;

public class RepositoryFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

  private MainActivity mMainActivity;
  private Instrumentation mInstrumentation;
  private RecyclerView mRecyclerView;
  private FrameLayout mFrameLayout;
  private RepositoryFragment repositoryFragment;

  public RepositoryFragmentTest() {
    super(MainActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    this.mMainActivity = getActivity();
    this.mInstrumentation = getInstrumentation();
    this.mFrameLayout = this.mMainActivity.findViewById(R.id.frameConteiner);
    this.repositoryFragment = new RepositoryFragment();
    this.mMainActivity.getSupportFragmentManager().beginTransaction().add(mFrameLayout.getId(), repositoryFragment).commit();
    this.mInstrumentation.waitForIdleSync();
    this.mRecyclerView = repositoryFragment.getView().findViewById(R.id.recyclerViewRepository);
    SystemClock.sleep(6000);
  }

  @UiThreadTest
  public void testFirstObjectExists() throws Exception {
    View item = getItem(0);
    assertNotNull(item);
  }

  @UiThreadTest
  public void testNameRepositoryMustExist() throws Exception {
    View item = getItem(0);

    TextView name = item.findViewById(R.id.textRepository);

    assertNotNull(name.getText().toString());
  }

  @UiThreadTest
  public void testRecyclerViewHas30Itens() throws Exception {
    int countAdapter = this.mRecyclerView.getAdapter().getItemCount();
    assertEquals(30, countAdapter);
  }

  private View getItem(int index) {
    return this.mRecyclerView.getChildAt(index);
  }

}
