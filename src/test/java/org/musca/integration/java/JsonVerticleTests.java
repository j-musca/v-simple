package org.musca.integration.java;

import org.junit.Test;
import org.musca.message.MessagePojo;
import org.vertx.testtools.TestVerticle;

import java.time.LocalDate;

import static org.vertx.testtools.VertxAssert.testComplete;

/**
 * Created by jonas on 14.09.14.
 */
public class JsonVerticleTests extends TestVerticle {

    @Test
    public void testVerticle() {
        vertx.eventBus().send("some.message", new MessagePojo("", 1, LocalDate.now()).toJson());
        testComplete();
    }
}
