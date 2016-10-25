package pl.blackmonday.hornet.domain.api;

import pl.blackmonday.hornet.api.IServer;
import pl.blackmonday.hornet.domain.api.parsing.DataParser;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import rx.Observable;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class Api implements IApi {

    private IServer server;
    private DataParser parser;

    public Api(IServer server) {
        this.server = server;
        this.parser = new DataParser();
    }

    //==============================================================================================
    // API CALLS
    //==============================================================================================

    @Override
    public Observable<Void> authorize(String login, String password) {
        return server.refreshSession();
    }

    @Override
    public Observable<Bug> getBugs(long projectId) {
        return server.getBugs(projectId)
                .flatMap(Observable::from)
                .map(bugModel -> parser.parse(bugModel))
                .sorted((bug1, bug2) ->
                        bug2.getCreationDate().compareTo(bug1.getCreationDate()));
    }

    @Override
    public Observable<Void> refreshSession() {
        return server.refreshSession();
    }

    @Override
    public Observable<Project> getProjects() {
        return server.getProjects()
                .flatMap(Observable::from)
                .map(projectModel -> parser.parse(projectModel));
    }

    @Override
    public Observable<Project> getProject(long projectId) {
        return server.getProject(projectId)
                .map(projectModel -> parser.parse(projectModel));
    }

}
