package pl.blackmonday.hornet.local;

import pl.blackmonday.hornet.api.client.IClient;
import pl.blackmonday.hornet.local.internal.Dependencies;

/**
 * Created by Marcin Laskowski on 16.08.16.
 * Senfino 2016
 */

public class Playground {

    public static void main(String... args){
        IClient client = Dependencies.instanceOfClient();
    }

}
