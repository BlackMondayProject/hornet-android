package pl.blackmonday.hornet.ui.items.project;

import pl.blackmonday.hornet.model.project.Project;
import pl.blackmonday.hornet.ui.items.ItemDecorator;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class ProjectItemDecorator extends ItemDecorator<Project, ProjectItem>{

    public ProjectItemDecorator(Project data, ClickReceiver<Project> receiver) {
        super(data, receiver);
    }

    @Override
    public void decorate(ProjectItem projectItem) {
        projectItem.setName(data.getName());
    }
}
