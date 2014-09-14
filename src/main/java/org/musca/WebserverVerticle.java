package org.musca;

import org.musca.message.MessagePojo;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.platform.Verticle;

import java.time.LocalDate;

/**
 * Created by jonas on 13.09.14.
 */
public class WebserverVerticle extends Verticle {

    @Override
    public void start() {
        final RouteMatcher routeMatcher = new RouteMatcher();

        routeMatcher.get("/tag/:theTag", (HttpServerRequest request) -> {
            final String tag = request.params().get("theTag");
            container.logger().info(String.format("Looking for: %s", tag));

            final MessagePojo messagePojo = new MessagePojo("", 1, LocalDate.now());
            container.logger().info(String.format("Send pojo: %s", messagePojo.toString()));
            vertx.eventBus().send("some.message", messagePojo.toJson());
            //address as constant?
            vertx.eventBus().send("webclient.adress", tag, (Message<JsonObject> message) -> {
                final JsonObject flickrResponse = message.body();
                final Buffer html = new Buffer();

                html.appendString(String.format("<html><h1>Results for %s </h1>\n", tag));

                flickrResponse.getArray("items").forEach((final Object element) -> {
                    final JsonObject jsonElement = (JsonObject) element;
                    html.appendString(String.format("<h3>%s</h3>\n", jsonElement.getString("title")));
                    html.appendString(String.format("<a href=\"%s\">\n<img scr=\"%s\"/>\n</a><br/>\n", jsonElement.getString("link"),
                            ((JsonObject) jsonElement.getField("media")).getString("m")));
                    html.appendString(jsonElement.getString("description"));
                });

                html.appendString("</html>");
                request.response().end(html);
            });
        });

        routeMatcher.getWithRegEx(".*", (HttpServerRequest request) -> {
            request.response().end("ERROR: please fill the tag!");
        });

        vertx.createHttpServer().requestHandler(routeMatcher).listen(8080);
        container.logger().info("WebserverVerticle started");
    }
}
