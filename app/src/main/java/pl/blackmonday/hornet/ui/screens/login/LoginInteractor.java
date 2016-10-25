package pl.blackmonday.hornet.ui.screens.login;

import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.screens.base.BaseInteractor;
import pl.blackmonday.hornet.ui.screens.base.rx.AlwaysCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.EmptyDoneCallback;
import pl.blackmonday.hornet.ui.screens.base.rx.ErrorCallback;

/**
 * Created by Marcin Laskowski on 25.10.16.
 * Senfino 2016
 */

public class LoginInteractor extends BaseInteractor<LoginPresenter> {

    public LoginInteractor(LoginPresenter presenter, IApi api) {
        super(presenter, api);
    }

    public void authorize(String login, String password, EmptyDoneCallback done, ErrorCallback error, AlwaysCallback always){
        api.authorize(login, password)
                .subscribe(onUi(done, error, always));
    }

}
