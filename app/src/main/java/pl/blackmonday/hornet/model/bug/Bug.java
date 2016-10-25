package pl.blackmonday.hornet.model.bug;

import android.os.Parcel;
import android.os.Parcelable;

import org.joda.time.DateTime;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */


public class Bug implements Parcelable {

    public enum Priority{
        LOW, MEDIUM, HIGH, CRITICAL
    }

    long id;
    String title;
    String description;
    Priority priority;
    DateTime creationDate;

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
        return creationDate;
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

    //==============================================================================================
    //  PARCELABLE IMPLEMENTATION
    //==============================================================================================

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.priority == null ? -1 : this.priority.ordinal());
        dest.writeSerializable(this.creationDate);
    }

    protected Bug(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
        int tmpPriority = in.readInt();
        this.priority = tmpPriority == -1 ? null : Priority.values()[tmpPriority];
        this.creationDate = (DateTime) in.readSerializable();
    }

    public static final Parcelable.Creator<Bug> CREATOR = new Parcelable.Creator<Bug>() {
        @Override
        public Bug createFromParcel(Parcel source) {
            return new Bug(source);
        }

        @Override
        public Bug[] newArray(int size) {
            return new Bug[size];
        }
    };

}
