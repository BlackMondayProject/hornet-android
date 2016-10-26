package pl.blackmonday.hornet.ui.list.data.project;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;
import pl.blackmonday.hornet.R;

/**
 * Created by Marcin Laskowski on 26.10.16.
 * Senfino 2016
 */

class ProjectItemViewHolder extends FlexibleViewHolder {

    @BindView(R.id.itemProject_tvName)
    TextView tvName;

    ProjectItemViewHolder(View view, final FlexibleAdapter adapter) {
        super(view, adapter, false);
        ButterKnife.bind(this, view);
    }

    void bind(ProjectSnapshot project, OnProjectClickedListener listener){
        setName(project.name);
        setOnClickListener(project.id, listener);
    }

    private void setName(String name) {
        tvName.setText(name);
    }

    private void setOnClickListener(long id, OnProjectClickedListener listener) {
        getContentView().setOnClickListener(v -> listener.onProjectClicked(id));
    }

}
