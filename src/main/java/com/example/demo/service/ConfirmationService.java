package com.example.demo.service;

import com.example.demo.dto.VkMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService implements CallbackHandler {

    @Value("${vk.confirmation_response}")
    private String confirmationResponse;

    @Override
    public String handleCallback(VkMessage message) {
        return confirmationResponse;
    }

    @Override
    public String getType() {
        return "confirmation";
    }
}
