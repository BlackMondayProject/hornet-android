package pl.blackmonday.hornet.api.client;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import pl.blackmonday.hornet.api.IServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class RetrofitClient {

    private ILogger logger;

    public RetrofitClient setLogger(ILogger logger) {
        this.logger = logger;
        return this;
    }

    public IServer create() {
        return retrofit().create(IServer.class);
    }

    private Retrofit retrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(IServer.URL)
                .addCallAdapterFactory(rxAdapterFactory())
                .addConverterFactory(converterFactory())
                .build();
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor())
                .build();
    }

    private Interceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger::log);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private RxJavaCallAdapterFactory rxAdapterFactory(){
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    private GsonConverterFactory converterFactory(){
        return GsonConverterFactory.create();
    }

}
