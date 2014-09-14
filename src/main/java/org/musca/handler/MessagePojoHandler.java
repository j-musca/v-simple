package org.musca.handler;

import org.musca.message.MessagePojo;
import org.vertx.java.platform.Container;

/**
 * Created by jonas on 14.09.14.
 */
public class MessagePojoHandler extends AbstractPojoHandler<MessagePojo> {

    private final Container container;

    public MessagePojoHandler(Container container) {
        super(MessagePojo.class);
        this.container = container;
    }

    @Override
    protected void handlePojoMessage(MessagePojo messagePojo) {
        container.logger().info(String.format("Got pojo in concrete handler: %s", messagePojo.toString()));
    }
}
