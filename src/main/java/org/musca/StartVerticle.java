package org.musca;

import org.vertx.java.platform.Verticle;

/**
 * Created by jonas on 13.09.14.
 *
 * Main verticle that manages startup and configuration of other verticles.
 */
public class StartVerticle extends Verticle {

    @Override
    public void start() {
        container.deployVerticle("org.musca.ChatVerticle");
        container.deployVerticle("org.musca.PingVerticle");
        container.deployVerticle("org.musca.FlickrVerticle");
        container.deployVerticle("org.musca.WebserverVerticle");
        container.deployVerticle("org.musca.JsonVerticle");
    }
}
