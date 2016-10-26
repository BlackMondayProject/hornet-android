package pl.blackmonday.hornet.ui.list.adapter.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;
import pl.blackmonday.hornet.R;

/**
 * Created by Marcin Laskowski on 19.09.16.
 * Senfino 2016
 */

class ProjectAdapterEmptyItem extends AbstractFlexibleItem<ProjectAdapterEmptyItem.ViewHolder> {

    @Override
    public boolean equals(Object o) {
        return o instanceof ProjectAdapterEmptyItem;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_empty_project;
    }

    @Override
    public ProjectAdapterEmptyItem.ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        View layout = inflater.inflate(getLayoutRes(), parent, false);
        return new ProjectAdapterEmptyItem.ViewHolder(layout, adapter);
    }

    class ViewHolder extends FlexibleViewHolder {
        ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
        }
    }

}
