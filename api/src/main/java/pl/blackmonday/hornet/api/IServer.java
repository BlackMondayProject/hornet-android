package pl.blackmonday.hornet.api;


import java.util.List;

import pl.blackmonday.hornet.api.model.BugModel;
import pl.blackmonday.hornet.api.model.ProjectModel;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public interface IServer {

    String URL = "https://private-7936d-hornet1.apiary-mock.com";

    @GET("/projects")
    Observable<Void> refreshSession();

    @GET("/projects")
    Observable<List<ProjectModel>> getProjects();

    @GET("/projects/{project_id}")
    Observable<ProjectModel> getProject(
            @Path("project_id") long projectId
    );

    @GET("/projects/{project_id}/bugs")
    Observable<List<BugModel>> getBugs(
            @Path("project_id") long projectId
    );

    @GET("/projects/{project_id}/bugs/{bug_id}")
    Observable<BugModel> getBug(
            @Path("project_id") long projectId,
            @Path("bug_id") long bugId
    );

}
