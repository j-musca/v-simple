package org.musca;

import org.musca.representation.ParsedFlickrJson;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.java.platform.Verticle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by jonas on 13.09.14.
 */
public class FlickrVerticle extends Verticle {

    @Override
    public void start() {
        HttpClient client = vertx.createHttpClient().setHost("ycpi-api.flickr.com").setPort(80);

        vertx.eventBus().registerHandler("webclient.adress", (Message<String> message) -> {
            container.logger().info(String.format("Received tag: %s", message.body()));

            try {
                final String decodedMessage = URLDecoder.decode(message.body(), "UTF-8");
                final String url = String.format("/services/feeds/photos_public.gne?tags=%s&format=json", decodedMessage);
                client.getNow(url, (HttpClientResponse response) -> {
                    response.bodyHandler( (Buffer buffer) -> {
                        try {
                            final ParsedFlickrJson parsedFlickrJson = new ParsedFlickrJson(buffer.toString());
                            message.reply(parsedFlickrJson.getParsedContent());
                        } catch (IOException e) {
                            container.logger().error("FlickrVerticle with json", e);
                            message.reply("Error");
                        }
                    });
                });
            } catch (UnsupportedEncodingException e) {
                container.logger().error("FlickrVerticle error", e);
            }
        });
        container.logger().info("FlickrVerticle started");
    }
}
