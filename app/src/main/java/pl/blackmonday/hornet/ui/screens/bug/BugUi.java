package pl.blackmonday.hornet.ui.screens.bug;

import org.joda.time.DateTime;

import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.ui.screens.base.BaseUi;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

public interface BugUi extends BaseUi {

    void setBugId(long id);

    void setBugTitle(String title);

    void setBugPriority(Bug.Priority priority);

    void setBugCreationDate(DateTime creationDate);

    void setBugDescription(String description);

}
