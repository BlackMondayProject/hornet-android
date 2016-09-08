package pl.blackmonday.hornet.api.client;

/**
 * Created by Marcin Laskowski on 28.06.16.
 * Senfino 2016
 */

public enum ApiError {
    NETWORK,
    CONVERSION,
    NOT_FOUND,
    UNAUTHORIZED,
    UNKNOWN;

    public static ApiError fromHttpCode(int code){
        switch (code){
            case 404:
                return NOT_FOUND;
            case 403:
                return UNAUTHORIZED;
        }

        return UNKNOWN;
    }

}
