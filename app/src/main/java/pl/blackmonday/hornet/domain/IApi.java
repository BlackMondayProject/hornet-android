package pl.blackmonday.hornet.domain;

import org.jdeferred.Promise;

import pl.blackmonday.hornet.api.client.ApiError;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public interface IApi {
    Promise<Void, ApiError, Void> authorize(String login, String password);
}
