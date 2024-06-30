package com.example.demo.controller;

import com.example.demo.dto.VkMessage;
import com.example.demo.service.CallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VKBot")
public class VkController {
    private static final Logger logger = LoggerFactory.getLogger(VkController.class);
    private final List<CallbackHandler> callbackHandlers;

    @Autowired
    public VkController(List<CallbackHandler> callbackHandlers) {
        this.callbackHandlers = callbackHandlers;
    }

    @PostMapping
    public String handleCallback(@RequestBody VkMessage message) {
        Optional<CallbackHandler> handler = callbackHandlers.stream()
                .filter(h -> h.getType().equals(message.getType()))
                .findFirst();

        if (handler.isPresent()) {
            return handler.get().handleCallback(message);
        } else {
            logger.warn("No handler found for message type: " + message.getType());
        }

        return "ok";
    }
}
