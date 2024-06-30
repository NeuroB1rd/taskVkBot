package com.example.demo.service;

import com.example.demo.dto.VkMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConfirmationServiceTest {

    private ConfirmationService confirmationService;

    @Value("${vk.confirmation_response}")
    private String confirmationResponse;

    @BeforeEach
    void setUp() {
        confirmationService = new ConfirmationService();
        ReflectionTestUtils.setField(confirmationService, "confirmationResponse", confirmationResponse);
    }

    @Test
    void handleCallback() {
        VkMessage message = new VkMessage();

        String response = confirmationService.handleCallback(message);

        assertEquals(confirmationResponse, response);
    }

    @Test
    void getType() {
        String type = confirmationService.getType();

        assertEquals("confirmation", type);
    }
}
