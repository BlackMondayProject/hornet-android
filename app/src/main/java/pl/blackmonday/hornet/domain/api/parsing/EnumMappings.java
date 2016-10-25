package pl.blackmonday.hornet.domain.api.parsing;

import pl.blackmonday.hornet.model.bug.Bug;

/**
 * Created by Marcin Laskowski on 22.09.16.
 * Senfino 2016
 */


class EnumMappings {

    static Bug.Priority bugPriority(String priority) {
        switch (priority) {
            case "low":
                return Bug.Priority.LOW;
            case "medium":
                return Bug.Priority.MEDIUM;
            case "high":
                return Bug.Priority.HIGH;
            case "critical":
                return Bug.Priority.CRITICAL;
            default:
                throw new IllegalArgumentException("unknown bug priority");
        }
    }

}
