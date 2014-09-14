package org.musca;

import org.musca.handler.ChatMessageHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static org.musca.AddressRegistry.CHAT_MESSAGE;

public class ChatVerticle extends Verticle {

    @Override
    public void start() {
        vertx.createNetServer().connectHandler((NetSocket socket) -> {
            final String address = socket.remoteAddress().toString();

//            Lambda-way
//            vertx.eventBus().registerHandler(CHAT_MESSAGE, (Message<String> message) -> {
//                socket.write(message.body());
//            });
            vertx.eventBus().registerHandler(CHAT_MESSAGE, new ChatMessageHandler(socket));

            socket.write(String.format("Welcome to chat %s!", address));

            socket.dataHandler((Buffer buffer) -> {
                final String timestamp = DateTimeFormatter.ISO_TIME.format(LocalTime.now());
                final String message = String.format("%s <%s> %s", timestamp, address, buffer.toString());
                vertx.eventBus().publish(CHAT_MESSAGE, message);
            });

            socket.closeHandler((Void v) -> {
                final String message = String.format("%s has left the chat", address);
                vertx.eventBus().publish(CHAT_MESSAGE, message);
            });

        }).listen(1234);

        container.logger().info("ChatVerticle started");
    }
}
