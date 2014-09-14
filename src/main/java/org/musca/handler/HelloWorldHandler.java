package org.musca.handler;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;

/**
 * Created by jonas on 10.09.14.
 */
public class HelloWorldHandler implements Handler<HttpServerRequest> {

    private final Vertx vertx;

    public HelloWorldHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(HttpServerRequest request) {
        request.response().end("Hello World!");
        vertx.eventBus().publish("ping-address", "Message for you");
    }
}
