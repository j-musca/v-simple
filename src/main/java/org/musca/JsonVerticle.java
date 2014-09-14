package org.musca;

import org.musca.handler.MessagePojoHandler;
import org.vertx.java.platform.Verticle;

/**
 * Created by jonas on 13.09.14.
 */
public class JsonVerticle extends Verticle {

    @Override
    public void start() {
        vertx.eventBus().registerHandler("some.message", new MessagePojoHandler(container));
        container.logger().info("JsonVerticle started");
    }
}
