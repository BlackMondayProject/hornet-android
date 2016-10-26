package pl.blackmonday.hornet.ui.screens.home;

import java.util.Collections;
import java.util.List;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import pl.blackmonday.hornet.ui.list.data.bug.BugSnapshot;
import pl.blackmonday.hornet.ui.list.data.project.ProjectSnapshot;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BasePresenter;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

public class HomePresenter
        extends BasePresenter<HomeUi> {

    //==============================================================================================
    // FIELDS
    //==============================================================================================

    private HomeInteractor interactor;
    private List<Project> projects = Collections.emptyList();
    private List<Bug> bugs = Collections.emptyList();
    private Project selectedProject = null;

    //==============================================================================================
    // CREATION
    //==============================================================================================

    public HomePresenter(HomeUi ui, Navigator navigator, IApi api) {
        super(ui, navigator);
        interactor = new HomeInteractor(this, api);
    }

    //==============================================================================================
    // LIFECYCLE
    //==============================================================================================

    @Override
    public void onCreate() {
        super.onCreate();
        syncProjects();
    }

    @Override
    public void onUpClicked() {
        ui.openDrawer();
    }

    @Override
    public boolean onBackPressed() {
        if (ui.isDrawerOpen()) {
            ui.closeDrawer();
            return true;
        }
        return false;
    }

    //==============================================================================================
    // METHODS CALLED BY UI
    //==============================================================================================

    public void onProjectClicked(long projectId) {
        Project project = getProject(projectId);
        if (project != null) {
            ui.closeDrawer();
            setSelectedProject(project);
            syncBugsBlocking();
        }
    }

    public void onBugClicked(long bugId) {
        Bug bug = getBug(bugId);
        if (bug != null) {
            navigator.goToBugScreen(bug);
        }
    }

    public void onSwipePulled() {
        syncBugsNonBlocking();
    }

    //==============================================================================================
    // METHODS CALLED BY INTERACTOR
    //==============================================================================================

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        List<ProjectSnapshot> snapshots = ProjectSnapshot.from(projects);
        ui.onProjectsAcquired(snapshots);
    }

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        ui.setSelectedProject(project.getName());
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
        List<BugSnapshot> snapshots = BugSnapshot.from(bugs);
        ui.onBugsAcquired(snapshots);
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    //==============================================================================================
    // PRIVATE METHODS
    //==============================================================================================

    private void syncProjects() {
        ui.showProgress();
        interactor.syncAllProjects(
                this::setBugs,
                this::handleError,
                ui::hideProgress);
    }

    private void syncBugsBlocking() {
        ui.showProgress();
        interactor.syncProject(selectedProject,
                this::setBugs,
                this::handleError,
                ui::hideProgress);
    }

    private void syncBugsNonBlocking() {
        interactor.syncProject(selectedProject,
                this::setBugs,
                this::handleError,
                ui::hideSwipe);
    }

    private Bug getBug(long bugId) {
        for (Bug bug : bugs){
            if (bug.getId() == bugId){
                return bug;
            }
        }
        return null;
    }

    private Project getProject(long projectId) {
        for (Project project : projects){
            if (project.getId() == projectId){
                return project;
            }
        }
        return null;
    }

}
