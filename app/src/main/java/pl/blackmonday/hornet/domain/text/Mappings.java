package pl.blackmonday.hornet.domain.text;

import android.support.annotation.ColorRes;

import pl.blackmonday.hornet.R;
import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 23.09.16.
 * Senfino 2016
 */


public class Mappings {

    private Mappings() {
    }

    @ColorRes
    public static int getColor(Bug.Priority priority) {
        switch (priority) {
            case LOW:
                return R.color.priority_low;
            case MEDIUM:
                return R.color.priority_medium;
            case HIGH:
                return R.color.priority_high;
            case CRITICAL:
                return R.color.priority_critical;
            default:
                throw new IllegalArgumentException();
        }
    }

}
