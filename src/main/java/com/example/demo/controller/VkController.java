package com.example.demo.controller;

import com.example.demo.dto.VkMessage;
import com.example.demo.service.CallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/VKBot")
public class VkController {

    private final List<CallbackHandler> callbackHandlers;

    @Autowired
    public VkController(List<CallbackHandler> callbackHandlers) {
        this.callbackHandlers = callbackHandlers;
    }

    @PostMapping
    public String handleCallback(@RequestBody VkMessage message) {
        for (CallbackHandler handler : callbackHandlers) {
            if (handler.getType().equals(message.getType())) {
                return handler.handleCallback(message);
            }
        }
        return "ok";
    }
}
