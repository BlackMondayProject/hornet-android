package pl.blackmonday.hornet.ui.screens.home;

import java.util.List;

import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;
import pl.blackmonday.hornet.ui.screens.base.BaseUi;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

public interface HomeUi extends BaseUi {

    void setSelectedProject(String projectName);

    void onProjectsAcquired(List<Project> projects);

    void onBugsAcquired(List<Bug> bugs);

    void hideSwipe();
}
