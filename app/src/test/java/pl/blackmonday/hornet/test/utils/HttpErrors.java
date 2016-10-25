package pl.blackmonday.hornet.test.utils;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by Marcin Laskowski on 19.09.16.
 * Senfino 2016
 */


public class HttpErrors {

    public static <T> Observable<T> create(int errorCode){
        MediaType mediaType = MediaType.parse("application/json");
        ResponseBody body = ResponseBody.create(mediaType, "");
        Response response = Response.error(errorCode, body);
        return Observable.error(new HttpException(response));
    }

}
