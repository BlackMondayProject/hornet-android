package pl.blackmonday.hornet.domain.api;

import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import rx.Observable;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public interface IApi {

    Observable<Void> authorize(String login, String password);

    Observable<Bug> getBugs(long projectId);

    Observable<Void> refreshSession();

    Observable<Project> getProjects();

    Observable<Project> getProject(long projectId);
}
