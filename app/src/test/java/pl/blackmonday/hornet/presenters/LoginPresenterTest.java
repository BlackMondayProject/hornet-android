package pl.blackmonday.hornet.presenters;

import org.jdeferred.Promise;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import pl.blackmonday.hornet.api.client.ApiError;
import pl.blackmonday.hornet.common.Promises;
import pl.blackmonday.hornet.domain.IApi;
import pl.blackmonday.hornet.ui.screens.login.LoginPresenter;
import pl.blackmonday.hornet.ui.screens.login.LoginUi;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest {

    @Mock
    LoginUi ui;
    @Mock
    IApi api;

    LoginPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(ui, api);
    }

    @Test
    public void testSuccessfulAuthorization() {
        when(ui.getLogin()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "user";
            }
        });
        when(ui.getPassword()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "qwerty";
            }
        });
        when(api.authorize("user", "qwerty")).then(new Answer<Promise<Void, ApiError, Void>>() {
            @Override
            public Promise<Void, ApiError, Void> answer(InvocationOnMock invocation) throws Throwable {
                return Promises.success(null);
            }
        });

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).getPassword();
        verify(api).authorize("user", "qwerty");
        verify(ui).goToHomeScreen();
        verify(ui, never()).notifyInvalidCredentials();
    }

    @Test
    public void testNoLoginAuthorization() {
        when(ui.getLogin()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "";
            }
        });
        when(ui.getPassword()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "qwerty";
            }
        });

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).notifyNoLogin();
        verify(ui, never()).goToHomeScreen();
    }

    @Test
    public void testNoPasswordAuthorization() {
        when(ui.getLogin()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "user";
            }
        });
        when(ui.getPassword()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "";
            }
        });

        presenter.onDoneClicked();

        verify(ui).getPassword();
        verify(ui).notifyNoPassword();
        verify(ui, never()).goToHomeScreen();
    }

    @Test
    public void testInvalidCredentialsAuthorization() {
        when(ui.getLogin()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "user";
            }
        });
        when(ui.getPassword()).then(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                return "qwerty";
            }
        });
        when(api.authorize("user", "qwerty")).then(new Answer<Promise<Void, ApiError, Void>>() {
            @Override
            public Promise<Void, ApiError, Void> answer(InvocationOnMock invocation) throws Throwable {
                return Promises.fail(null);
            }
        });

        presenter.onDoneClicked();

        verify(ui).getLogin();
        verify(ui).getPassword();
        verify(api).authorize("user", "qwerty");
        verify(ui).notifyInvalidCredentials();
        verify(ui, never()).goToHomeScreen();
    }
}