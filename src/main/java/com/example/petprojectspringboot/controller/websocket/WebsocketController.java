package com.example.petprojectspringboot.controller.websocket;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebsocketController {
    @MessageMapping("/connect")
    public void connect(SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("Connect to websocket");
    }
}
