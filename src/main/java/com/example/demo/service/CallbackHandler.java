package com.example.demo.service;


import com.example.demo.dto.VkMessage;

public interface CallbackHandler {
    String handleCallback(VkMessage message);
    String getType();
}
