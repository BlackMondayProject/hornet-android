package pl.blackmonday.hornet.domain.api.parsing;

/**
 * Created by Marcin Laskowski on 22.09.16.
 * Senfino 2016
 */


public class ParserException extends IllegalArgumentException {

    ParserException(String message, Throwable cause) {
        super(message, cause);
    }

}
