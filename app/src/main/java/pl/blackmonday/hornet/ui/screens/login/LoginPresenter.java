package pl.blackmonday.hornet.ui.screens.login;

import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;

import pl.blackmonday.hornet.api.client.ApiError;
import pl.blackmonday.hornet.domain.IApi;
import pl.blackmonday.hornet.ui.screens.base.BasePresenter;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public class LoginPresenter
        extends BasePresenter<LoginUi> {

    public LoginPresenter(LoginUi ui, IApi api) {
        super(ui, api);
    }

    public void onDoneClicked() {
        String password = getPassword();
        String login = getLogin();
        if (login.isEmpty() || password.isEmpty()) {
            return;
        }

        api.authorize(login, password).done(new DoneCallback<Void>() {
            @Override
            public void onDone(Void result) {
                ui.goToHomeScreen();
            }
        }).fail(new FailCallback<ApiError>() {
            @Override
            public void onFail(ApiError result) {
                ui.notifyInvalidCredentials();
            }
        });

    }

    private String getLogin() {
        String login = ui.getLogin();
        if (login.isEmpty()) {
            ui.notifyNoLogin();
            return "";
        } else {
            return login;
        }
    }

    private String getPassword() {
        String password = ui.getPassword();
        if (password.isEmpty()) {
            ui.notifyNoPassword();
            return "";
        } else {
            return password;
        }
    }
}
