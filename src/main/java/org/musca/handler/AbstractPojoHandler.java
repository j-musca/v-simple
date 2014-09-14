package org.musca.handler;

import com.google.gson.Gson;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;

/**
 * Created by jonas on 14.09.14.
 */
public abstract class AbstractPojoHandler<T> implements Handler<Message<String>> {

    private final Class<T> messageType;

    public AbstractPojoHandler(Class<T> messageType) {
        this.messageType = messageType;
    }

    @Override
    public void handle(Message<String> message) {
        final T pojoMessage = getPojoFromJson(message.body());
        handlePojoMessage(pojoMessage);
    }

    protected abstract void handlePojoMessage(T pojoMessage);

    protected T getPojoFromJson(final String json) {
        return new Gson().fromJson(json, messageType);
    }

}
