package org.musca.representation;

import org.vertx.java.core.json.JsonObject;

import java.io.IOException;

/**
 * Created by jonas on 13.09.14.
 */
public class ParsedFlickrJson {

    private final JsonObject parsedContent;

    public ParsedFlickrJson(String sourceContent) throws IOException {
        this.parsedContent = new JsonObject(getRepairedFlickrJson(sourceContent));
    }

    public JsonObject getParsedContent() {
        return parsedContent;
    }

    private String getRepairedFlickrJson(final String jsonContent) {
        return jsonContent.substring(0, jsonContent.length() - 1)
                .replaceFirst("jsonFlickrFeed\\(", "")
                .trim()
                .replaceAll("\\\\'", "'");
    }
}
