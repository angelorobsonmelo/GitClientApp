package com.desafiosafaty.angelorobson.fragment;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.support.test.annotation.UiThreadTest;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.desafiosafaty.angelorobson.R;
import com.desafiosafaty.angelorobson.activity.MainActivity;
import com.desafiosafaty.angelorobson.dao.RepositoryDAO;
import com.desafiosafaty.angelorobson.model.Repository;

import static com.desafiosafaty.angelorobson.builder.RepositoryBuilder.oneRepository;


public class PullRequestFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

  private MainActivity mMainActivity;
  private Instrumentation mInstrumentation;
  private RecyclerView mRecyclerView;
  private FrameLayout mFrameLayout;
  private PullRequestFragment pullRequestFragment;
  private RepositoryDAO repositoryDAO;

  public PullRequestFragmentTest() {
    super(MainActivity.class);
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    repositoryDAO = new RepositoryDAO(getActivity());
    Repository repository = oneRepository().builder();
    repositoryDAO.save(repository);
    this.mMainActivity = getActivity();
    this.mInstrumentation = getInstrumentation();
    this.mFrameLayout = this.mMainActivity.findViewById(R.id.frameConteiner);
    this.pullRequestFragment = new PullRequestFragment();
    this.mMainActivity.getSupportFragmentManager().beginTransaction().replace(mFrameLayout.getId(), pullRequestFragment).commit();
    this.mInstrumentation.waitForIdleSync();
    SystemClock.sleep(6000);
    this.mRecyclerView = pullRequestFragment.getView().findViewById(R.id.recyclerViewPullRequest);

  }

  @UiThreadTest
  public void testFirstObjectExists() throws Exception {
    View item = getItem(0);
    assertNotNull(item);
  }

  @UiThreadTest
  public void testTitlePullRequestMustExist() throws Exception {
    View item = getItem(0);

    TextView title = item.findViewById(R.id.textTitle);

    assertNotNull(title.getText().toString());
  }

  private View getItem(int index) {
    return this.mRecyclerView.getChildAt(index);
  }
}
