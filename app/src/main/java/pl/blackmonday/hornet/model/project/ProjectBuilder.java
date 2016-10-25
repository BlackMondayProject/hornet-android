package pl.blackmonday.hornet.model.project;

import java.util.Objects;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public class ProjectBuilder {

    private final Project project;

    public ProjectBuilder(){
        project = new Project();
    }

    public ProjectBuilder setId(long id){
        project.id = id;
        return this;
    }

    public ProjectBuilder setName(String name){
        project.name = name;
        return this;
    }

    public Project build(){
        Objects.requireNonNull(project.name);
        return project;
    }

}
