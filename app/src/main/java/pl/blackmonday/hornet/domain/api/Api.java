package pl.blackmonday.hornet.domain.api;

import java.util.List;

import pl.blackmonday.hornet.api.IServer;
import pl.blackmonday.hornet.domain.api.parsing.DataParser;
import pl.blackmonday.hornet.domain.api.sorting.DataSorter;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import rx.Observable;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class Api implements IApi {

    private IServer server;
    private DataParser parse;
    private DataSorter sort;

    public Api(IServer server) {
        this.server = server;
        this.parse = new DataParser();
        this.sort = new DataSorter();
    }

    //==============================================================================================
    // API CALLS
    //==============================================================================================

    @Override
    public Observable<Void> authorize(String login, String password) {
        return server.refreshSession();
    }

    @Override
    public Observable<List<Bug>> getBugs(long projectId) {
        return server.getBugs(projectId)
                .flatMap(Observable::from)
                .map(parse::bug)
                .sorted(sort::byCreationDate)
                .toList();
    }

    @Override
    public Observable<Void> refreshSession() {
        return server.refreshSession();
    }

    @Override
    public Observable<List<Project>> getProjects() {
        return server.getProjects()
                .flatMap(Observable::from)
                .map(parse::project)
                .toList();
    }

    @Override
    public Observable<Project> getProject(long projectId) {
        return server.getProject(projectId)
                .map(parse::project);
    }

}
