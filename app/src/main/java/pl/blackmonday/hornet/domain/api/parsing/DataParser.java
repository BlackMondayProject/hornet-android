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

    public Bug parse(BugModel bugModel) {
        try {
            return core.parseBug(bugModel);
        } catch (Exception e) {
            throw new ParserException("failed to parse bug " + bugModel.id, e);
        }
    }

    public Project parse(ProjectModel projectModel) {
        try {
            return core.parseProject(projectModel);
        } catch (Exception e) {
            throw new ParserException("failed to parse project " + projectModel.id, e);
        }
    }

}
