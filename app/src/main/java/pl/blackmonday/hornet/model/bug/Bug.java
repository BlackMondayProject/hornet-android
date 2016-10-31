package pl.blackmonday.hornet.model.bug;

import org.joda.time.DateTime;
import org.parceler.Parcel;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

@Parcel
public class Bug {

    public enum Priority{
        LOW, MEDIUM, HIGH, CRITICAL
    }

    long id;
    String title;
    String description;
    Priority priority;
//    @ParcelPropertyConverter(JodaDateTimeConverter.class) // FIXME
//    DateTime creationDate2;
    long creationDate;

    Bug() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public DateTime getCreationDate() {
//        return creationDate; // FIXME
        return new DateTime(creationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bug bug = (Bug) o;

        return id == bug.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return title + " (" + id + ")";
    }

}
