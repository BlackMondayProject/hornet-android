package pl.blackmonday.hornet.api.endpoints;

import java.util.List;

import pl.blackmonday.hornet.api.model.ProjectModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public interface MainServerInterface {

    @GET("/projects")
    Call<Void> refreshSession();

    @GET("/projects")
    Call<List<ProjectModel>> getProjects();

    @GET("/projects/{id}")
    Call<ProjectModel> getProject(
            @Path("id") long projectId
    );

}
