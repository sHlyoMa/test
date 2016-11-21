package com.enya.test.web;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/chat", decoders = ChatMessageDecoder.class, encoders = ChatMessageEncoder.class)
public class ChatEndpoint {

    @OnOpen
    public void open(final Session session) {
    }

    @OnMessage
    public void onMessage(final Session session, final ChatMessage chatMessage) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(chatMessage);
                }
            }
        } catch (IOException | EncodeException e) {
            System.err.println("errro");
        }
    }
}
