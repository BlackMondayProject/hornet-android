package pl.blackmonday.hornet.api.client;

import com.google.gson.JsonSyntaxException;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

import java.util.List;

import pl.blackmonday.hornet.api.endpoints.MainServerInterface;
import pl.blackmonday.hornet.api.model.ProjectModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class Client implements IClient {

    private MainServerInterface serverInterface;

    public Client(MainServerInterface serverInterface){
        this.serverInterface = serverInterface;
    }

    //==============================================================================================
    // ENDPOINTS
    //==============================================================================================

    @Override
    public Promise<Void, ApiError, Void> refreshSession() {
        return wrapWithPromise(serverInterface.refreshSession());
    }

    @Override
    public Promise<List<ProjectModel>, ApiError, Void> getProjects(){
        return wrapWithPromise(serverInterface.getProjects());
    }

    @Override
    public Promise<ProjectModel, ApiError, Void> getProject(long projectId){
        return wrapWithPromise(serverInterface.getProject(projectId));
    }

    //==============================================================================================
    // WRAPPING CALL WITH PROMISE
    //==============================================================================================

    private <T> Promise<T, ApiError, Void> wrapWithPromise(Call<T> call){
        final Deferred<T, ApiError, Void> deferred = new DeferredObject<>();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                handleResponse(deferred, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleFailure(deferred, t);
            }
        });
        return deferred.promise();
    }

    private <T> void handleResponse(Deferred<T, ApiError, Void> deferred, Response<T> response){
        if (response.isSuccessful()){
            deferred.resolve(response.body());
        } else {
            ApiError error = ApiError.fromHttpCode(response.code());
            deferred.reject(error);
        }
    }

    private <T> void handleFailure(Deferred<T, ApiError, Void> deferred, Throwable t){
        if (t instanceof JsonSyntaxException){
            deferred.reject(ApiError.CONVERSION);
        } else {
            deferred.reject(ApiError.NETWORK);
        }
    }

}
