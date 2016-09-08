package pl.blackmonday.hornet.domain;

import org.jdeferred.DonePipe;
import org.jdeferred.FailPipe;
import org.jdeferred.Promise;

import java.util.List;

import pl.blackmonday.hornet.api.client.ApiError;
import pl.blackmonday.hornet.api.client.IClient;
import pl.blackmonday.hornet.api.model.ProjectModel;
import pl.blackmonday.hornet.common.Promises;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class Api implements IApi{

    private IClient client;

    public Api(IClient client){
        this.client = client;
    }

    //==============================================================================================
    // API CALLS
    //==============================================================================================

    @Override
    public Promise<Void, ApiError, Void> authorize(String login, String password) {
        return client.refreshSession();
    }

    public Promise<Void, ApiError, Void> refreshSession(){
        return client.refreshSession();
    }

    public Promise<List<ProjectModel>, ApiError, Void> getProjects(){
        return ensureAuthorization(client.getProjects());
    }

    public Promise<ProjectModel, ApiError, Void> getProject(long projectId){
        return ensureAuthorization(client.getProject(projectId));
    }

    //==============================================================================================
    // AUTHORIZATION
    //==============================================================================================

    private <T> Promise<T, ApiError, Void> ensureAuthorization(final Promise<T, ApiError, Void> promise){
        return promise.then(null, new FailPipe<ApiError, T, ApiError, Void>() {
            @Override
            public Promise<T, ApiError, Void> pipeFail(ApiError result) {
                if (result.equals(ApiError.UNAUTHORIZED)){
                    return refreshSessionAndTryAgain(promise);
                } else {
                    return Promises.fail(result);
                }
            }
        });
    }

    private <T> Promise<T, ApiError, Void> refreshSessionAndTryAgain(final Promise<T, ApiError, Void> promise){
        return refreshSession().then(new DonePipe<Void, T, ApiError, Void>() {
            @Override
            public Promise<T, ApiError, Void> pipeDone(Void result) {
                return promise;
            }
        });
    }

}
