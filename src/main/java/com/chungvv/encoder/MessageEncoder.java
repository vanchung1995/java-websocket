package com.chungvv.encoder;

import com.chungvv.entity.Message;
import jakarta.json.Json;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class MessageEncoder implements Encoder.Text<Message> {
    @Override
    public String encode(Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("message", message.getMessage())
                .add("sender", message.getSender())
                .add("received", "")
                .build().toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
