package pl.blackmonday.hornet.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marcin Laskowski on 08.09.16.
 * Senfino 2016
 */

public class BugModel {
    @SerializedName("id")
    public long id;
    @SerializedName("title")
    public String title;
    @SerializedName("creationDate")
    public long creationDate;
    @SerializedName("description")
    public String description;
    @SerializedName("priority")
    public String priority;
}
