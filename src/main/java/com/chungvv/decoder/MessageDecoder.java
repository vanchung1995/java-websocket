package com.chungvv.decoder;

import com.chungvv.entity.Message;
import jakarta.json.Json;
import jakarta.json.JsonObject;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.Date;

public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String s) throws DecodeException {
        Message message = new Message();
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        message.setMessage(jsonObject.getString("message"));
        message.setSender(jsonObject.getString("sender"));
        message.setReceived(new Date());
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
