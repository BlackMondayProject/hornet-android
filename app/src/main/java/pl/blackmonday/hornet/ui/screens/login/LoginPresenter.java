package pl.blackmonday.hornet.ui.screens.login;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BasePresenter;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public class LoginPresenter
        extends BasePresenter<LoginUi> {

    //==============================================================================================
    // FIELDS
    //==============================================================================================

    private LoginInteractor interactor;

    //==============================================================================================
    // CREATION
    //==============================================================================================

    public LoginPresenter(LoginUi ui, Navigator navigator, IApi api) {
        super(ui, navigator);
        interactor = new LoginInteractor(this, api);
    }

    //==============================================================================================
    // METHODS CALLED BY UI
    //==============================================================================================

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

    public void onDoneClicked() {
        String login = getLogin();
        String password = getPassword();
        if (login.isEmpty() || password.isEmpty()) {
            return;
        }
        authorize(login, password);
    }

    private void authorize(String login, String password) {
        ui.showProgress();
        interactor.authorize(login, password,
                navigator::goToHomeScreen,
                this::handleAuthorizationError,
                ui::hideProgress);
    }

    private void handleAuthorizationError(Throwable throwable) {
        handleError(throwable, errorCode -> {
            switch (errorCode) {
                case 403:
                    ui.notifyInvalidCredentials();
                    return true;
                default:
                    return false;
            }
        });
    }

}
