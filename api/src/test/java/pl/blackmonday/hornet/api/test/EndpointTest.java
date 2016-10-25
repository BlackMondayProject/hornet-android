package pl.blackmonday.hornet.api.test;


import org.junit.Before;
import org.junit.Test;

import pl.blackmonday.hornet.api.IServer;
import pl.blackmonday.hornet.api.client.RetrofitClient;
import pl.blackmonday.hornet.api.model.BugModel;
import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class EndpointTest {

    private IServer server;

    @Before
    public void setUp(){
        server = new RetrofitClient()
                .setLogger(text -> {})
                .create();
    }

    @Test
    public void simpleTest(){
        TestSubscriber<BugModel> testObserver = new TestSubscriber<>();

        server.getBugs(34)
                .flatMap(Observable::from)
                .subscribe(testObserver);

        testObserver.assertValueCount(0);
    }

}
