package com.example.demo.service;

import com.example.demo.dto.VkMessage;
import com.example.demo.dto.VkMessageDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService implements CallbackHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Value("${vk.token}")
    private String token;

    @Value("${vk.secret}")
    private String secret;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async("taskExecutor")
    public void sendMessage(int userId, String message) {
        String url = String.format("https://api.vk.com/method/messages.send?user_id=%d&message=%s&access_token=%s&v=5.131&random_id=%d",
                userId, message, token, new java.util.Random().nextInt());
        restTemplate.getForObject(url, String.class);
    }

    @Override
    public String handleCallback(VkMessage message) {
        logger.info("Received message: {}", message);
        if (secret.equals(message.getSecret())) {
            VkMessageDetails messageDetails = message.getObject().getMessage();
            int userId = messageDetails.getFromId();
            String text = messageDetails.getText();
            String responseText = "Вы сказали: " + text;
            logger.info("Sending message to user {}: {}", userId, responseText);
            sendMessage(userId, responseText);
        } else {
            logger.warn("Invalid secret key");
        }
        return "ok";
    }

    @Override
    public String getType() {
        return "message_new";
    }
}
