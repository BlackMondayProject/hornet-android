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

    public void syncData(DoneCallback<List<Bug>> done, ErrorCallback error, AlwaysCallback always) {
        api.getProjects()
                .toList()
                .doOnNext(onUi(presenter::setProjects))
                .flatMapIterable(projects -> projects)
                .first()
                .doOnNext(onUi(presenter::setSelectedProject))
                .flatMap(project -> api.getBugs(project.getId()))
                .toList()
                .subscribe(onUi(done, error, always));
    }

    public void getBugs(DoneCallback<List<Bug>> done, ErrorCallback error, AlwaysCallback always) {
        getSelectedProject()
                .flatMap(project -> api.getBugs(project.getId()))
                .toList()
                .subscribe(onUi(done, error, always));
    }

    private Observable<Project> getSelectedProject() {
        Project project = presenter.getSelectedProject();
        if (project != null) {
            return Observable.just(project);
        } else {
            return api.getProjects().first();
        }
    }

}
