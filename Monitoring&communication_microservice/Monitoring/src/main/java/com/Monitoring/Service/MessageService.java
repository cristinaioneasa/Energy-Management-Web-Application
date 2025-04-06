package com.Monitoring.Service;

import com.Monitoring.Entity.Message;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(Message mesaj) {
        messagingTemplate.convertAndSend("/user", mesaj);
    }


}
