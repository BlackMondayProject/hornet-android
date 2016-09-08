package pl.blackmonday.hornet.ui.screens.login;

import pl.blackmonday.hornet.ui.screens.base.BaseUi;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public interface LoginUi extends BaseUi {
    void goToHomeScreen();
    String getLogin();
    String getPassword();
    void notifyNoLogin();
    void notifyNoPassword();
    void notifyInvalidCredentials();
}
