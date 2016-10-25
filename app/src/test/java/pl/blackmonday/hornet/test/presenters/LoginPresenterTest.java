package pl.blackmonday.hornet.test.presenters;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.login.LoginPresenter;
import pl.blackmonday.hornet.ui.screens.login.LoginUi;
import pl.blackmonday.hornet.test.utils.HttpErrors;
import pl.blackmonday.hornet.test.utils.RxTestRule;
import rx.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Rule
    public RxTestRule _rule = new RxTestRule();

    @Mock
    LoginUi ui;
    @Mock
    IApi api;
    @Mock
    Navigator navigator;

    private LoginPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(ui, navigator, api);
    }

    @Test
    public void testSuccessfulAuthorization() {
        when(ui.getLogin()).thenReturn("user");
        when(ui.getPassword()).thenReturn("qwerty");
        when(api.authorize("user", "qwerty")).thenReturn(Observable.just(null));

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).getPassword();
        verify(api).authorize("user", "qwerty");
        verify(navigator).goToHomeScreen();
        verify(ui, never()).notifyInvalidCredentials();
    }

    @Test
    public void testNoLoginAuthorization() {
        when(ui.getLogin()).thenReturn("");
        when(ui.getPassword()).thenReturn("qwerty");

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).notifyNoLogin();
        verify(navigator, never()).goToHomeScreen();
    }

    @Test
    public void testNoPasswordAuthorization() {
        when(ui.getLogin()).thenReturn("user");
        when(ui.getPassword()).thenReturn("");

        presenter.onDoneClicked();

        verify(ui).getPassword();
        verify(ui).notifyNoPassword();
        verify(navigator, never()).goToHomeScreen();
    }

    @Test
    public void testInvalidCredentialsAuthorization() {
        when(ui.getLogin()).thenReturn("user");
        when(ui.getPassword()).thenReturn("qwerty");
        when(api.authorize("user", "qwerty")).thenReturn(HttpErrors.create(403));

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).getPassword();
        verify(api).authorize("user", "qwerty");
        verify(ui).notifyInvalidCredentials();
        verify(navigator, never()).goToHomeScreen();
    }

}