package pl.blackmonday.hornet.ui.items.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import pl.blackmonday.hornet.model.project.Project;

/**
 * Created by Marcin Laskowski on 19.09.16.
 * Senfino 2016
 */


public class ProjectAdapter extends FlexibleAdapter<AbstractFlexibleItem>{

    private ProjectItem.OnClickListener listener;

    public ProjectAdapter(ProjectItem.OnClickListener listener) {
        super(Collections.emptyList());
        this.listener = listener;
    }

    public void updateData(List<Project> projects){
        List<AbstractFlexibleItem> projectItems = new ArrayList<>();
        for (Project project : projects){
            projectItems.add(new ProjectItem(project, listener));
        }
        if (projectItems.isEmpty()) projectItems.add(new NoProjectsItem());

        updateDataSet(projectItems);
        notifyDataSetChanged();
    }

}
