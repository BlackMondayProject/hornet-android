package pl.blackmonday.hornet.ui.screens.home;

import java.util.List;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
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

    public void onProjectClicked(Project project) {
        ui.closeDrawer();
        setSelectedProject(project);
        syncBugsBlocking();
    }

    public void onBugClicked(Bug bug) {
        navigator.goToBugScreen(bug);
    }

    public void onSwipePulled() {
        syncBugsNonBlocking();
    }

    //==============================================================================================
    // METHODS CALLED BY INTERACTOR
    //==============================================================================================

    public void setProjects(List<Project> projects) {
        ui.onProjectsAcquired(projects);
    }

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
        ui.setSelectedProject(project.getName());
    }

    public void setBugs(List<Bug> bugs) {
        ui.onBugsAcquired(bugs);
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

}
