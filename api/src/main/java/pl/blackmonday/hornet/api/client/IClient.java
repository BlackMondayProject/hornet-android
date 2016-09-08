package pl.blackmonday.hornet.api.client;

import org.jdeferred.Promise;

import java.util.List;

import pl.blackmonday.hornet.api.model.ProjectModel;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public interface IClient {
    Promise<Void, ApiError, Void> refreshSession();

    Promise<List<ProjectModel>, ApiError, Void> getProjects();

    Promise<ProjectModel, ApiError, Void> getProject(long projectId);
}
