package org.musca.handler;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.net.NetSocket;

/**
 * Created by jonas on 13.09.14.
 */
public class ChatMessageHandler implements Handler<Message<String>> {

    private final NetSocket socket;

    public ChatMessageHandler(NetSocket socket) {
        this.socket = socket;
    }

    @Override
    public void handle(Message<String> message) {
        socket.write(message.body());
    }
}
