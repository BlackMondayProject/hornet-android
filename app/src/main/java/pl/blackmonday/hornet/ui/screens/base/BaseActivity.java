package pl.blackmonday.hornet.ui.screens.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import pl.blackmonday.hornet.Dependencies;
import pl.blackmonday.hornet.domain.IApi;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Marcin Laskowski on 19.06.16.
 * Senfino 2016
 */

public abstract class BaseActivity<Presenter extends BasePresenter>
        extends Activity
        implements BaseUi {

    protected Presenter presenter;

    @LayoutRes
    @Nullable
    protected abstract Integer provideLayoutId();

    @LayoutRes
    @NonNull
    protected abstract Presenter providePresenter(IApi api);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
        setLayout();
    }

    private void setPresenter() {
        IApi api = Dependencies.instanceOfApi();
        presenter = providePresenter(api);
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

}
