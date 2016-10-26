package pl.blackmonday.hornet.ui.screens.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.list.adapter.bug.BugAdapter;
import pl.blackmonday.hornet.ui.list.adapter.project.ProjectAdapter;
import pl.blackmonday.hornet.ui.list.data.bug.BugSnapshot;
import pl.blackmonday.hornet.ui.list.data.project.ProjectSnapshot;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BaseActivity;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by Marcin Laskowski on 21.09.16.
 * Senfino 2016
 */

public class HomeActivity
        extends BaseActivity<HomePresenter>
        implements HomeUi {

    //==============================================================================================
    // VIEWS
    //==============================================================================================

    @BindView(R.id.vToolbar)
    Toolbar vToolbar;
    @BindView(R.id.activityHome_vDrawerLayout)
    DrawerLayout vDrawerLayout;
    @BindView(R.id.activityHome_rvProjects)
    RecyclerView rvProjects;
    @BindView(R.id.activityHome_vSwipe)
    SwipeRefreshLayout vSwipe;
    @BindView(R.id.activityHome_rvBugs)
    RecyclerView rvBugs;

    //==============================================================================================
    // FIELDS
    //==============================================================================================

    private ProjectAdapter projectAdapter;
    private BugAdapter bugAdapter;

    //==============================================================================================
    // CREATION
    //==============================================================================================

    @Nullable
    @Override
    protected Integer provideLayoutId() {
        return R.layout.activity_home;
    }

    @NonNull
    @Override
    protected HomePresenter providePresenter(Navigator navigator, IApi api) {
        return new HomePresenter(this, navigator, api);
    }

    //==============================================================================================
    // LIFECYCLE
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpToolbar();
        setUpProjectRecyclerView();
        setUpBugRecyclerView();
        setUpSwipe();
    }

    //==============================================================================================
    // LISTENERS
    //==============================================================================================

    public void onProjectClicked(long projectId) {
        presenter.onProjectClicked(projectId);
    }

    public void onBugClicked(long bugId) {
        presenter.onBugClicked(bugId);
    }

    public void onSwipePulled() {
        presenter.onSwipePulled();
    }

    private void onUpClicked(View view) {
        presenter.onUpClicked();
    }

    //==============================================================================================
    // UI IMPLEMENTATION
    //==============================================================================================

    @Override
    public void setSelectedProject(String projectName) {
        vToolbar.setTitle(projectName);
    }

    @Override
    public void onProjectsAcquired(List<ProjectSnapshot> projects) {
        projectAdapter.updateData(projects);
    }

    @Override
    public void onBugsAcquired(List<BugSnapshot> bugs) {
        bugAdapter.updateData(bugs);
    }

    @Override
    public void hideSwipe() {
        vSwipe.setRefreshing(false);
    }

    @Override
    public void openDrawer() {
        vDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        vDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean isDrawerOpen() {
        return vDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    //==============================================================================================
    // PRIVATE METHODS
    //==============================================================================================

    private void setUpToolbar() {
        vToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        vToolbar.setNavigationOnClickListener(this::onUpClicked);
    }

    private void setUpProjectRecyclerView() {
        projectAdapter = new ProjectAdapter(this::onProjectClicked);
        rvProjects.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        rvProjects.setAdapter(projectAdapter);
    }

    private void setUpBugRecyclerView() {
        bugAdapter = new BugAdapter(this::onBugClicked);
        rvBugs.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        rvBugs.setAdapter(bugAdapter);
    }

    private void setUpSwipe() {
        vSwipe.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        vSwipe.setColorSchemeResources(R.color.colorAccent);
        vSwipe.setOnRefreshListener(this::onSwipePulled);
    }

}
