package pl.blackmonday.hornet.domain.api.parsing;

import org.joda.time.DateTime;

/**
 * Created by Marcin Laskowski on 22.09.16.
 * Senfino 2016
 */


class Utils {

    static DateTime fromTimestamp(long timestamp){
        return new DateTime(timestamp * 1000);
    }

}
