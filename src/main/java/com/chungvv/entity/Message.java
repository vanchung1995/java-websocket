package com.chungvv.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Message {
    private String message;
    private String sender;
    private Date received;
}
