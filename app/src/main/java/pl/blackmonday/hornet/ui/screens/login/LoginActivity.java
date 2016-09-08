package pl.blackmonday.hornet.ui.screens.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.IApi;
import pl.blackmonday.hornet.ui.screens.base.BaseActivity;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public class LoginActivity
        extends BaseActivity<LoginPresenter>
        implements LoginUi {

    @BindView(R.id.activityLogin_etLogin)
    EditText etLogin;
    @BindView(R.id.activityLogin_etPassword)
    EditText etPassword;

    @Nullable
    @Override
    protected Integer provideLayoutId() {
        return R.layout.activity_login;
    }

    @NonNull
    @Override
    protected LoginPresenter providePresenter(IApi api) {
        return new LoginPresenter(this, api);
    }

    @OnClick(R.id.activityLogin_btnDone)
    void onDoneClicked(){
        presenter.onDoneClicked();
    }

    @Override
    public void goToHomeScreen() {
        Toast.makeText(this, "SUKCES!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getLogin() {
        return etLogin.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void notifyNoLogin() {
        Toast.makeText(this, "Podaj nazwę użytkownika", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyNoPassword() {
        Toast.makeText(this, "Podaj hasło", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyInvalidCredentials() {
        Toast.makeText(this, "Zły login lub hasło", Toast.LENGTH_SHORT).show();
    }
}
