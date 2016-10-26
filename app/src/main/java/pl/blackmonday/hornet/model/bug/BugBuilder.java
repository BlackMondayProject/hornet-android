package pl.blackmonday.hornet.model.bug;

import org.joda.time.DateTime;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */


public class BugBuilder {

    private final Bug bug;

    public BugBuilder(){
        bug = new Bug();
    }

    public BugBuilder setId(long id) {
        bug.id = id;
        return this;
    }

    public BugBuilder setTitle(String title) {
        bug.title = title;
        return this;
    }

    public BugBuilder setDescription(String description) {
        bug.description = description;
        return this;
    }

    public BugBuilder setPriority(Bug.Priority priority){
        bug.priority = priority;
        return this;
    }

    public BugBuilder setCreationDate(DateTime creationDate) {
//        bug.creationDate = creationDate; // FIXME
        bug.creationDate = creationDate.getMillis();
        return this;
    }

    public Bug build() {
        return bug;
    }

}
