package com.chatapplication.chatapplication.controller;

import com.chatapplication.chatapplication.model.Message;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class controller {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/message")// /app/message
    @SendTo("/chatroom/public")
    private Message receivePublicMessage(@Payload Message message){
        return message;
    }
    @MessageMapping("/private-message")
    private Message receivePrivateMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getMessage(),"/private",message);  // /user/david/message
        return message;
    }
}
