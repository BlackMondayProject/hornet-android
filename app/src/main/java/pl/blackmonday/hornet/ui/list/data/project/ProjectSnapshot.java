package pl.blackmonday.hornet.ui.list.data.project;

import java.util.ArrayList;
import java.util.List;

import pl.blackmonday.hornet.model.project.Project;

/**
 * Created by Marcin Laskowski on 26.10.16.
 * Senfino 2016
 */

public class ProjectSnapshot {

    long id;
    String name;

    public ProjectSnapshot(Project origin) {
        id = origin.getId();
        name = origin.getName();
    }

    public static List<ProjectSnapshot> from(List<Project> projects){
        List<ProjectSnapshot> snapshots = new ArrayList<>();
        for (Project project : projects){
            snapshots.add(new ProjectSnapshot(project));
        }
        return snapshots;
    }

}
