package com.chungvv.client;

import com.chungvv.decoder.MessageDecoder;
import com.chungvv.encoder.MessageEncoder;
import com.chungvv.entity.Message;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.text.SimpleDateFormat;

@javax.websocket.ClientEndpoint(encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class ClientEndpoint {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    @OnOpen
    public void onOpen(Session session) {
        System.out.printf("Connection is established. Session id: %s\n", session.getId());
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        System.out.printf("[%s:%s] %s\n", simpleDateFormat.format(message.getReceived()), message.getSender(), message.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.printf("%s has closed", session.getId());
    }
}
