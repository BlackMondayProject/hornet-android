package pl.blackmonday.hornet.ui.items.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;
import pl.blackmonday.hornet.R;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class ProjectAdapterItem extends AbstractFlexibleItem<ProjectAdapterItem.ViewHolder> {

    private ProjectItemDecorator decorator;

    public ProjectAdapterItem(ProjectItemDecorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ProjectAdapterItem) {
            ProjectAdapterItem that = (ProjectAdapterItem) other;
            return this.decorator.equals(that.decorator);
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_project;
    }

    @Override
    public ViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ViewHolder viewHolder, int position, List payloads) {
        decorator.decorate(viewHolder);
    }

    class ViewHolder extends FlexibleViewHolder implements ProjectItem{

        @BindView(R.id.itemProject_tvName)
        TextView tvName;

        public ViewHolder(View view, final FlexibleAdapter adapter) {
            super(view, adapter, false);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decorator.onClick();
                }
            });
        }

        @Override
        public void setName(String name) {
            tvName.setText(name);
        }

    }

}
