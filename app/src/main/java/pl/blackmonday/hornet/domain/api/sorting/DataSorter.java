package pl.blackmonday.hornet.domain.api.sorting;

import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 25.10.16.
 * Senfino 2016
 */

public class DataSorter {

    public int byCreationDate(Bug bug1, Bug bug2){
        return bug2.getCreationDate().compareTo(bug1.getCreationDate());
    }

}
