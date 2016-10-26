package pl.blackmonday.hornet.ui.list.adapter.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import pl.blackmonday.hornet.ui.list.data.project.OnProjectClickedListener;
import pl.blackmonday.hornet.ui.list.data.project.ProjectItem;
import pl.blackmonday.hornet.ui.list.data.project.ProjectSnapshot;

/**
 * Created by Marcin Laskowski on 19.09.16.
 * Senfino 2016
 */


public class ProjectAdapter extends FlexibleAdapter<AbstractFlexibleItem>{

    private OnProjectClickedListener listener;

    public ProjectAdapter(OnProjectClickedListener listener) {
        super(Collections.emptyList());
        this.listener = listener;
    }

    public void updateData(List<ProjectSnapshot> projects){
        List<AbstractFlexibleItem> projectItems = new ArrayList<>();
        for (ProjectSnapshot project : projects){
            projectItems.add(new ProjectItem(project, listener));
        }
        if (projectItems.isEmpty()) projectItems.add(new ProjectAdapterEmptyItem());

        updateDataSet(projectItems);
        notifyDataSetChanged();
    }

}
