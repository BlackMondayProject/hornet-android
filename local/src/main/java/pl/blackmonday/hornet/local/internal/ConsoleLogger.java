package pl.blackmonday.hornet.local.internal;

import pl.blackmonday.hornet.api.retrofit.Logger;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class ConsoleLogger implements Logger{
    @Override
    public void log(String text) {
        System.out.println(text);
    }
}
