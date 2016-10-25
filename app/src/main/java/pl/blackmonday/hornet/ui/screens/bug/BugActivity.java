package pl.blackmonday.hornet.ui.screens.bug;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import butterknife.BindView;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.api.IApi;
import pl.blackmonday.hornet.domain.text.FormatUtils;
import pl.blackmonday.hornet.domain.text.Mappings;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.ui.navigation.Navigator;
import pl.blackmonday.hornet.ui.screens.base.BaseActivity;

/**
 * Created by Marcin Laskowski on 21.09.16.
 * Senfino 2016
 */


public class BugActivity
        extends BaseActivity<BugPresenter>
        implements BugUi {

    @BindView(R.id.vToolbar)
    Toolbar vToolbar;
    @BindView(R.id.activityBug_ivPriority)
    ImageView ivPriority;
    @BindView(R.id.activityBug_tvTitle)
    TextView tvTitle;
    @BindView(R.id.activityBug_tvCreationDate)
    TextView tvCreationDate;
    @BindView(R.id.activityBug_tvDescription)
    TextView tvDescription;

    @Nullable
    @Override
    protected Integer provideLayoutId() {
        return R.layout.activity_bug;
    }

    @NonNull
    @Override
    protected BugPresenter providePresenter(Navigator navigator, IApi api) {
        return new BugPresenter(this, navigator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
    }

    private void setToolbar() {
        vToolbar.setNavigationIcon(R.drawable.ic_back);
        vToolbar.setNavigationOnClickListener(v -> onNavigateUp());
    }

    @Override
    public void setBugId(long id) {
        String title = FormatUtils.formatId(id);
        vToolbar.setTitle(title);
    }

    @Override
    public void setBugTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void setBugPriority(Bug.Priority priority) {
        ivPriority.setBackgroundResource(Mappings.getColor(priority));
    }

    @Override
    public void setBugCreationDate(DateTime creationDate) {
        tvCreationDate.setText(FormatUtils.formatDate(creationDate));
    }

    @Override
    public void setBugDescription(String description) {
        tvDescription.setText(description);
    }

}
