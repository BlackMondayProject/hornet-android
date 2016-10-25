package pl.blackmonday.hornet.ui.screens.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.ui.navigation.ActivityNavigator;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public abstract class BaseActivity<Presenter extends BasePresenter>
        extends AppCompatActivity
        implements BaseUi {

    @Nullable
    @BindView(R.id.vLoader)
    View vLoader;

    protected Presenter presenter;

    @LayoutRes
    @Nullable
    protected abstract Integer provideLayoutId();

    @NonNull
    protected abstract Presenter providePresenter(Navigator navigator, IApi api);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
        setLayout();
        presenter.onCreate();
    }

    private void setPresenter() {
        DependencyContainer container = new DependencyContainer();
        Navigator navigator = new ActivityNavigator(this);
        presenter = providePresenter(navigator, container.api);
    }

    private void setLayout() {
        Integer layoutId = provideLayoutId();
        if (layoutId != null) {
            setContentView(layoutId);
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void showProgress() {
        assert vLoader != null;
        vLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        assert vLoader != null;
        vLoader.setVisibility(View.GONE);
    }

}
