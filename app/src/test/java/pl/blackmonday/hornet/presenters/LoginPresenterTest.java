package pl.blackmonday.hornet.presenters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.login.LoginPresenter;
import pl.blackmonday.hornet.ui.screens.login.LoginUi;
import rx.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

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
        when(ui.getLogin()).then(answer -> "user");
        when(ui.getPassword()).then(answer -> "qwerty");
        when(api.authorize("user", "qwerty")).then(answer -> Observable.just(null));

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).getPassword();
        verify(api).authorize("user", "qwerty");
        verify(navigator).goToHomeScreen();
        verify(ui, never()).notifyInvalidCredentials();
    }

    @Test
    public void testNoLoginAuthorization() {
        when(ui.getLogin()).then(answer -> "");
        when(ui.getPassword()).then(answer -> "qwerty");

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).notifyNoLogin();
        verify(navigator, never()).goToHomeScreen();
    }

    @Test
    public void testNoPasswordAuthorization() {
        when(ui.getLogin()).then(answer -> "user");
        when(ui.getPassword()).then(answer -> "");

        presenter.onDoneClicked();

        verify(ui).getPassword();
        verify(ui).notifyNoPassword();
        verify(navigator, never()).goToHomeScreen();
    }

    @Test
    public void testInvalidCredentialsAuthorization() {
        when(ui.getLogin()).then(answer -> "user");
        when(ui.getPassword()).then(answer -> "qwerty");
        when(api.authorize("user", "qwerty")).then(answer -> HttpErrors.create(403));

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).getPassword();
        verify(api).authorize("user", "qwerty");
        verify(ui).notifyInvalidCredentials();
        verify(navigator, never()).goToHomeScreen();
    }

}