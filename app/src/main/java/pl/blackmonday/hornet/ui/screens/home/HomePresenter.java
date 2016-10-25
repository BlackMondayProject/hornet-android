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

    private HomeInteractor interactor;
    private Project selectedProject = null;

    public HomePresenter(HomeUi ui, Navigator navigator, IApi api) {
        super(ui, navigator);
        interactor = new HomeInteractor(this, api);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        syncData();
    }

    private void syncData() {
        ui.showProgress();
        interactor.syncData(
                this::setBugs,
                this::handleError,
                ui::hideProgress);
    }

    //==============================================================================================
    // METHODS CALLED BY UI
    //==============================================================================================

    public void onProjectClicked(Project project) {
        setSelectedProject(project);
        ui.showProgress();
        interactor.getBugs(
                this::setBugs,
                this::handleError,
                ui::hideProgress);
    }

    public void onBugClicked(Bug bug) {
        navigator.goToBugScreen(bug);
    }

    public void onSwipePulled() {
        interactor.getBugs(
                this::setBugs,
                this::handleError,
                ui::hideSwipe);
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

}
