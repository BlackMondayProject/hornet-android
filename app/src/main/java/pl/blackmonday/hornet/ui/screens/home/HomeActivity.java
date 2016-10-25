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

import java.util.List;

import butterknife.BindView;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import pl.blackmonday.hornet.ui.items.bug.BugAdapter;
import pl.blackmonday.hornet.ui.items.bug.BugItem;
import pl.blackmonday.hornet.ui.items.project.ProjectAdapter;
import pl.blackmonday.hornet.ui.items.project.ProjectItem;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BaseActivity;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by Marcin Laskowski on 21.09.16.
 * Senfino 2016
 */


public class HomeActivity
        extends BaseActivity<HomePresenter>
        implements HomeUi, ProjectItem.OnClickListener, BugItem.OnClickListener {

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

    private ProjectAdapter projectAdapter;
    private BugAdapter bugAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        setProjectRecyclerView();
        setBugRecyclerView();
        setSwipeToRefresh();
    }

    private void setToolbar() {
        vToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        vToolbar.setNavigationOnClickListener(v -> openDrawer());
    }

    private void setProjectRecyclerView() {
        projectAdapter = new ProjectAdapter();
        rvProjects.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        rvProjects.setAdapter(projectAdapter);
    }

    private void setBugRecyclerView() {
        bugAdapter = new BugAdapter();
        rvBugs.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
        rvBugs.setAdapter(bugAdapter);
    }

    private void setSwipeToRefresh() {
        vSwipe.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        vSwipe.setColorSchemeResources(R.color.colorAccent);
        vSwipe.setOnRefreshListener(() -> presenter.onSwipePulled());
    }

    @Override
    public void setSelectedProject(String projectName){
        vToolbar.setTitle(projectName);
    }

    @Override
    public void onProjectsAcquired(List<Project> projects) {
        projectAdapter.updateData(projects, this);
    }

    @Override
    public void onBugsAcquired(List<Bug> bugs) {
        bugAdapter.updateData(bugs, this);
    }

    @Override
    public void hideSwipe() {
        vSwipe.setRefreshing(false);
    }

    @Override
    public void onClick(Project project) {
        presenter.onProjectClicked(project);
        closeDrawer();
    }

    @Override
    public void onClick(Bug bug) {
        presenter.onBugClicked(bug);
    }

    private void openDrawer(){
        vDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void closeDrawer(){
        vDrawerLayout.closeDrawer(GravityCompat.START);
    }

}
