package pl.blackmonday.hornet.api.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.blackmonday.hornet.api.endpoints.MainServerInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class RetrofitClient {

    private Logger logger;
    private String url;

    public RetrofitClient setLogger(Logger logger){
        this.logger = logger;
        return this;
    }

    public RetrofitClient setUrl(String url){
        this.url = url;
        return this;
    }

    public MainServerInterface create(){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(createLoggingInterceptor(logger))
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(MainServerInterface.class);
    }

    private Interceptor createLoggingInterceptor(final Logger logger) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                logger.log(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
