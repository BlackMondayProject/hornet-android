package pl.blackmonday.hornet.ui.screens.home;

import java.util.List;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import pl.blackmonday.hornet.ui.screens.base.BaseInteractor;
import pl.blackmonday.hornet.ui.screens.base.rx.AlwaysCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.DoneCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.ErrorCallback;
import rx.Observable;

/**
 * Created by Marcin Laskowski on 10.10.16.
 * Senfino 2016
 */


public class HomeInteractor extends BaseInteractor<HomePresenter> {

    public HomeInteractor(HomePresenter presenter, IApi api) {
        super(presenter, api);
    }

    //==============================================================================================
    // PUBLIC METHODS
    //==============================================================================================

    public void syncAllProjects(DoneCallback<List<Bug>> done, ErrorCallback error, AlwaysCallback always) {
        syncSelectedProject()
                .compose(getBugsFromProject())
                .subscribe(onUi(done, error, always));
    }

    public void syncProject(Project selectedProject, DoneCallback<List<Bug>> done, ErrorCallback error, AlwaysCallback always) {
        Observable.just(selectedProject)
                .compose(getBugsFromProject())
                .subscribe(onUi(done, error, always));
    }

    //==============================================================================================
    // PRIVATE METHODS
    //==============================================================================================

    private Observable<Project> syncSelectedProject() {
        return getSelectedProject()
                .doOnNext(onUi(presenter::setSelectedProject));
    }

    private Observable<Project> getSelectedProject() {
        Project project = presenter.getSelectedProject();
        if (project != null) {
            return Observable.just(project);
        } else {
            return syncAllProjects()
                    .map(projects -> projects.get(0));
        }
    }

    private Observable<List<Project>> syncAllProjects() {
        return api.getProjects()
                .doOnNext(onUi(presenter::setProjects));
    }

    private Observable.Transformer<Project, List<Bug>> getBugsFromProject() {
        return observable -> observable.map(Project::getId)
                .flatMap(api::getBugs);
    }

}
