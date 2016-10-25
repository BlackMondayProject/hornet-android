package pl.blackmonday.hornet.ui.items.bug;

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

public class NoBugsItem extends AbstractFlexibleItem<NoBugsItem.ViewHolder> {

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_empty_bug;
    }

    @Override
    public NoBugsItem.ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new NoBugsItem.ViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    class ViewHolder extends FlexibleViewHolder {
        ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
        }
    }

}
