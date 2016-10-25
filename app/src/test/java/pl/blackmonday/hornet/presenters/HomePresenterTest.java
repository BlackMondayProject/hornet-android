package pl.blackmonday.hornet.presenters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.home.HomePresenter;
import pl.blackmonday.hornet.ui.screens.home.HomeUi;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Marcin Laskowski on 09.10.16.
 * Senfino 2016
 */


public class HomePresenterTest {

    @Mock
    HomeUi ui;
    @Mock
    Navigator navigator;
    @Mock
    IApi api;

    HomePresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        RxUtils.setUp();
        presenter = new HomePresenter(ui, navigator, api);
    }

    @After
    public void tearDown() {
        RxUtils.tearDown();
    }

    @Test
    public void testOnCreate(){
        when(api.getProjects()).thenReturn(Observable.empty());

        presenter.onCreate();

        verify(api).getProjects();
    }

}
