package com.enya.test.web;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.text.SimpleDateFormat;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final ChatMessage chatMessage) throws EncodeException {
        return Json.createObjectBuilder()
                .add("message", chatMessage.getMessage())
                .add("sender", chatMessage.getSender())
                .add("received", sdf.format(chatMessage.getReceived())).build()
                .toString();
    }
}
