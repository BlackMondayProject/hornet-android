package pl.blackmonday.hornet.domain.text;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Marcin Laskowski on 22.09.16.
 * Senfino 2016
 */


public class FormatUtils {

    private static DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    private FormatUtils() {
    }

    public static String formatDate(DateTime dateTime){
        return dateTime.toString(dateFormatter);
    }

    public static String formatId(long id) {
        return "#" + id;
    }
}
