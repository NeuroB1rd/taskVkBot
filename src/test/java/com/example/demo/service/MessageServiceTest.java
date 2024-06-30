package com.example.demo.service;

import com.example.demo.dto.VkMessage;
import com.example.demo.dto.VkMessageDetails;
import com.example.demo.dto.VkMessageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageServiceTest {

    private MessageService messageService;
    private RestTemplate restTemplate;

    @Value("${vk.token}")
    private String token;

    @Value("${vk.secret}")
    private String secret;

    @BeforeEach
    void setUp() {
        restTemplate = Mockito.mock(RestTemplate.class);
        messageService = new MessageService();
        ReflectionTestUtils.setField(messageService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(messageService, "token", token);
        ReflectionTestUtils.setField(messageService, "secret", secret);
    }

    @Test
    void sendMessage() {
        int userId = 123456;
        String message = "Hello, world!";

        messageService.sendMessage(userId, message);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate).getForObject(captor.capture(), eq(String.class));

        String expectedUrl = String.format("https://api.vk.com/method/messages.send?user_id=%d&message=%s&access_token=%s&v=5.131&random_id=%d",
                userId, message, token, new Random().nextInt());
        assertEquals(expectedUrl.split("&random_id=")[0], captor.getValue().split("&random_id=")[0]);
    }

    @Test
    void handleCallback_withValidSecret() {
        VkMessage message = new VkMessage();
        VkMessageObject object = new VkMessageObject();
        VkMessageDetails messageDetails = new VkMessageDetails();

        messageDetails.setFromId(123456);
        messageDetails.setText("Hello!");
        object.setMessage(messageDetails);
        message.setObject(object);
        message.setSecret(secret);

        String response = messageService.handleCallback(message);

        assertEquals("ok", response);
        verify(restTemplate).getForObject(anyString(), eq(String.class));
    }

    @Test
    void handleCallback_withInvalidSecret() {
        VkMessage message = new VkMessage();
        VkMessageObject object = new VkMessageObject();
        VkMessageDetails messageDetails = new VkMessageDetails();

        messageDetails.setFromId(123456);
        messageDetails.setText("Hello!");
        object.setMessage(messageDetails);
        message.setObject(object);
        message.setSecret("invalid_secret");

        String response = messageService.handleCallback(message);

        assertEquals("ok", response);
        verify(restTemplate, never()).getForObject(anyString(), eq(String.class));
    }
}
