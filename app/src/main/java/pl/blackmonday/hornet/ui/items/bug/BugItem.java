package pl.blackmonday.hornet.ui.items.bug;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;
import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.domain.text.FormatUtils;
import pl.blackmonday.hornet.domain.text.Mappings;
import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class BugItem extends AbstractFlexibleItem<BugItem.ViewHolder> {

    private Bug bug;
    private OnClickListener listener = project -> {};

    BugItem(Bug bug, OnClickListener listener) {
        this(bug);
        if (listener != null) {
            this.listener = listener;
        }
    }

    BugItem(Bug bug) {
        this.bug = bug;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BugItem) {
            BugItem that = (BugItem) other;
            return this.bug.equals(that.bug);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_bug;
    }

    @Override
    public ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ViewHolder viewHolder, int position, List payloads) {
        viewHolder.tvId.setText(FormatUtils.formatId(bug.getId()));
        viewHolder.tvCreationDate.setText(FormatUtils.formatDate(bug.getCreationDate()));
        viewHolder.tvTitle.setText(bug.getTitle());
        viewHolder.ivPriority.setBackgroundResource(Mappings.getColor(bug.getPriority()));
        viewHolder.getContentView().setOnClickListener(v -> listener.onClick(bug));
    }

    class ViewHolder extends FlexibleViewHolder {

        @BindView(R.id.itemBug_tvId)
        TextView tvId;
        @BindView(R.id.itemBug_tvCreationDate)
        TextView tvCreationDate;
        @BindView(R.id.itemBug_tvTitle)
        TextView tvTitle;
        @BindView(R.id.itemBug_ivPriority)
        ImageView ivPriority;

        ViewHolder(View view, final FlexibleAdapter adapter) {
            super(view, adapter, false);
            ButterKnife.bind(this, view);
        }

    }

    public interface OnClickListener {
        void onClick(Bug bug);
    }

}
