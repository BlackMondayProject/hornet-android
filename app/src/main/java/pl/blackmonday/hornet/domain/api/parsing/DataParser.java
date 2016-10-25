package pl.blackmonday.hornet.domain.api.parsing;

import pl.blackmonday.hornet.api.model.BugModel;
import pl.blackmonday.hornet.api.model.ProjectModel;
import pl.blackmonday.hornet.model.bug.Bug;
import pl.blackmonday.hornet.model.project.Project;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */


public class DataParser {

    private DataParserCore core;

    public DataParser(){
        core = new DataParserCore();
    }

    public Bug bug(BugModel bugModel) {
        try {
            return core.parseBug(bugModel);
        } catch (Exception e) {
            throw new ParserException("failed to project bug " + bugModel.id, e);
        }
    }

    public Project project(ProjectModel projectModel) {
        try {
            return core.parseProject(projectModel);
        } catch (Exception e) {
            throw new ParserException("failed to project project " + projectModel.id, e);
        }
    }

}
