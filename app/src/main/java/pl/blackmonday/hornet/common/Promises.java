package pl.blackmonday.hornet.common;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class Promises {

    public static <Resolved, Rejected, Progress> Promise<Resolved, Rejected, Progress> success(Resolved resolved) {
        Deferred<Resolved, Rejected, Progress> deferred = new DeferredObject<>();
        deferred.resolve(resolved);
        return deferred.promise();
    }

    public static <Resolved, Rejected, Progress> Promise<Resolved, Rejected, Progress> fail(Rejected rejected) {
        Deferred<Resolved, Rejected, Progress> deferred = new DeferredObject<>();
        deferred.reject(rejected);
        return deferred.promise();
    }

}
