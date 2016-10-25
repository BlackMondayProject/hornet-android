package pl.blackmonday.hornet.test.presenters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.home.HomePresenter;
import pl.blackmonday.hornet.ui.screens.home.HomeUi;
import pl.blackmonday.hornet.test.utils.RxTestRule;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Marcin Laskowski on 09.10.16.
 * Senfino 2016
 */


public class HomePresenterTest {

    @Rule
    public RxTestRule _rule = new RxTestRule();

    @Mock
    HomeUi ui;
    @Mock
    Navigator navigator;
    @Mock
    IApi api;

    private HomePresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new HomePresenter(ui, navigator, api);
    }

    @Test
    public void testOnCreate(){
        when(api.getProjects()).thenReturn(Observable.empty());

        presenter.onCreate();

        verify(api).getProjects();
    }

}
