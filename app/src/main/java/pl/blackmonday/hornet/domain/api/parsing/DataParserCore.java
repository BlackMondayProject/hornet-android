package pl.blackmonday.hornet.domain.api.parsing;

import pl.blackmonday.hornet.api.model.BugModel;
import pl.blackmonday.hornet.api.model.ProjectModel;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.bug.BugBuilder;
import pl.blackmonday.hornet.model.project.Project;
import pl.blackmonday.hornet.model.project.ProjectBuilder;

import static pl.blackmonday.hornet.domain.api.parsing.Utils.fromTimestamp;

/**
 * Created by Marcin Laskowski on 22.09.16.
 * Senfino 2016
 */


class DataParserCore {

    Bug parseBug(BugModel bugModel){
        return new BugBuilder()
                .setId(bugModel.id)
                .setTitle(bugModel.title)
                .setDescription(bugModel.description)
                .setCreationDate(fromTimestamp(bugModel.creationDate))
                .setPriority(EnumMappings.bugPriority(bugModel.priority))
                .build();
    }

    Project parseProject(ProjectModel projectModel){
        return new ProjectBuilder()
                .setId(projectModel.id)
                .setName(projectModel.name)
                .build();
    }

}
