package pl.blackmonday.hornet.ui.list.data.bug;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import pl.blackmonday.hornet.R;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class BugItem extends AbstractFlexibleItem<BugItemViewHolder> {

    private BugSnapshot bug;
    private OnBugClickedListener listener;

    public BugItem(BugSnapshot bug, OnBugClickedListener listener) {
        this.bug = bug;
        setListener(listener);
    }

    private void setListener(OnBugClickedListener listener) {
        if (listener != null) {
            this.listener = listener;
        } else {
            this.listener = bugId -> {};
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof BugItem) {
            BugItem that = (BugItem) other;
            return this.bug.id == that.bug.id;
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_bug;
    }

    @Override
    public BugItemViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new BugItemViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, BugItemViewHolder viewHolder, int position, List payloads) {
        viewHolder.bind(bug, listener);
    }

}
