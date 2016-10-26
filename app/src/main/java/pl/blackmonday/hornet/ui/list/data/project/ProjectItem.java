package pl.blackmonday.hornet.ui.list.data.project;

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

public class ProjectItem extends AbstractFlexibleItem<ProjectItemViewHolder> {

    private ProjectSnapshot project;
    private OnProjectClickedListener listener;

    public ProjectItem(ProjectSnapshot project, OnProjectClickedListener listener) {
        this.project = project;
        setListener(listener);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ProjectItem) {
            ProjectItem that = (ProjectItem) other;
            return this.project.id == that.project.id;
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_project;
    }

    @Override
    public ProjectItemViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ProjectItemViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ProjectItemViewHolder viewHolder, int position, List payloads) {
        viewHolder.bind(project, listener);
    }

    private void setListener(OnProjectClickedListener listener) {
        if (listener != null) {
            this.listener = listener;
        } else {
            this.listener = projectId -> {};
        }
    }

}
