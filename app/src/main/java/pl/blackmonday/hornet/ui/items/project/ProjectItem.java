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
import pl.blackmonday.hornet.model.project.Project;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class ProjectItem extends AbstractFlexibleItem<ProjectItem.ViewHolder> {

    private Project project;
    private OnClickListener listener = project -> {};

    ProjectItem(Project project, OnClickListener listener) {
        this(project);
        if (listener != null) {
            this.listener = listener;
        }
    }

    ProjectItem(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ProjectItem) {
            ProjectItem that = (ProjectItem) other;
            return this.project.equals(that.project);
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
        viewHolder.tvName.setText(project.getName());
        viewHolder.getContentView().setOnClickListener(v -> listener.onClick(project));
    }

    class ViewHolder extends FlexibleViewHolder {

        @BindView(R.id.itemProject_tvName)
        TextView tvName;

        ViewHolder(View view, final FlexibleAdapter adapter) {
            super(view, adapter, false);
            ButterKnife.bind(this, view);
        }

    }

    public interface OnClickListener {
        void onClick(Project project);
    }

}
