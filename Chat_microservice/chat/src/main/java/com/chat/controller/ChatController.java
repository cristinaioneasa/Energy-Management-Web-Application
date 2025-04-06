package com.chat.controller;

import com.chat.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") //app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName().toString(), "/private", message); //user/*name*/private
        return message;
    }

    @MessageMapping("/typing/admin")
    public Message typingNotificationToClient(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName().toString(), "/typing/client", message);
        return message;
    }

    @MessageMapping("/typing/client")
    public Message typingNotificationToAdmin(@Payload Message message){

        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName().toString(), "/typing/admin", message);
        return message;
    }
}
