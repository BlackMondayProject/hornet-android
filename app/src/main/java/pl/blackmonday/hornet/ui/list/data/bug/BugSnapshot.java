package pl.blackmonday.hornet.ui.list.data.bug;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 26.10.16.
 * Senfino 2016
 */

public class BugSnapshot {

    long id;
    String title;
    DateTime creationDate;
    Bug.Priority priority;

    public BugSnapshot(Bug origin) {
        id = origin.getId();
        title = origin.getTitle();
        creationDate = origin.getCreationDate();
        priority = origin.getPriority();
    }

    public static List<BugSnapshot> from(List<Bug> bugs){
        List<BugSnapshot> snapshots = new ArrayList<>();
        for (Bug bug : bugs){
            snapshots.add(new BugSnapshot(bug));
        }
        return snapshots;
    }

}
