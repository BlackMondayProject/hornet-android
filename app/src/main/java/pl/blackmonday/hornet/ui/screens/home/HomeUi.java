package pl.blackmonday.hornet.ui.screens.home;

import java.util.List;

import pl.blackmonday.hornet.ui.list.data.bug.BugSnapshot;
import pl.blackmonday.hornet.ui.list.data.project.ProjectSnapshot;
import pl.blackmonday.hornet.ui.screens.base.BaseUi;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

public interface HomeUi extends BaseUi {

    void setSelectedProject(String projectName);

    void onProjectsAcquired(List<ProjectSnapshot> projects);

    void onBugsAcquired(List<BugSnapshot> bugs);

    void hideSwipe();

    void openDrawer();

    void closeDrawer();

    boolean isDrawerOpen();
}
