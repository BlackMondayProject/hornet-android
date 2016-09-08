package pl.blackmonday.hornet.model.project;

/**
 * Created by Marcin Laskowski on 25.06.16.
 * Senfino 2016
 */

public class Project {

    long id;
    String name;

    Project(){
        // only for builder
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        return id == project.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return name;
    }
}
