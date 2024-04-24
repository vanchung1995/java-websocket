package com.chungvv.server;

import com.chungvv.decoder.MessageDecoder;
import com.chungvv.encoder.MessageEncoder;
import com.chungvv.entity.Message;

import javax.websocket.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@javax.websocket.server.ServerEndpoint(value = "/api/chat", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class ServerEndpoint {
    public static Set<Session> peers = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.printf("%s joined the chat room\n", session.getId());
        peers.add(session);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        String user = (String) session.getUserProperties().get("user");
        if (Objects.isNull(user)) session.getUserProperties().put("user", message.getSender());

        if ("quit".equalsIgnoreCase(message.getMessage())) {
            System.out.printf("User %s has quited!\n", session.getUserProperties().get("user"));
            session.close();
        }

        System.out.printf("[%s:%s] %s\n", session.getId(), message.getReceived(), message.getMessage());;

        for (Session ss: peers) {
            if (!ss.getId().equals(session.getId())) {
                ss.getBasicRemote().sendObject(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) throws EncodeException, IOException {
        System.out.printf("%s has left the chat room!\n", session.getId());
        peers.remove(session);

        for (Session ss: peers) {
            Message message = new Message();
            message.setSender("Server");
            message.setMessage(String.format("%s has left the chat room!",session.getUserProperties().get("user")));
            ss.getBasicRemote().sendObject(message);
        }
    }
}
