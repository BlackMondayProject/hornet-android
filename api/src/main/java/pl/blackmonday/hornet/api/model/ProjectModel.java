package pl.blackmonday.hornet.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class ProjectModel {
    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;
}
