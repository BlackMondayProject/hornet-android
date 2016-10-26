package pl.blackmonday.hornet.ui.list.data.bug;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.text.FormatUtils;
import pl.blackmonday.hornet.domain.text.Mappings;
import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 26.10.16.
 * Senfino 2016
 */

class BugItemViewHolder extends FlexibleViewHolder {

    @BindView(R.id.itemBug_tvId)
    TextView tvId;
    @BindView(R.id.itemBug_tvCreationDate)
    TextView tvCreationDate;
    @BindView(R.id.itemBug_tvTitle)
    TextView tvTitle;
    @BindView(R.id.itemBug_ivPriority)
    ImageView ivPriority;

    BugItemViewHolder(View view, final FlexibleAdapter adapter) {
        super(view, adapter, false);
        ButterKnife.bind(this, view);
    }

    void bind(BugSnapshot bug, OnBugClickedListener listener){
        setId(bug.id);
        setCreationDate(bug.creationDate);
        setTitle(bug.title);
        setPriority(bug.priority);
        setOnClickListener(bug.id, listener);
    }

    private void setId(long id) {
        tvId.setText(FormatUtils.formatId(id));
    }

    private void setCreationDate(DateTime creationDate) {
        tvCreationDate.setText(FormatUtils.formatDate(creationDate));
    }

    private void setTitle(String title) {
        tvTitle.setText(title);
    }

    private void setPriority(Bug.Priority priority) {
        ivPriority.setBackgroundResource(Mappings.getColor(priority));
    }

    private void setOnClickListener(long id, OnBugClickedListener listener){
        getContentView().setOnClickListener(v -> listener.onBugClicked(id));
    }

}
