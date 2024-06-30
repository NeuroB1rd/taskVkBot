package com.example.demo.controller;

import com.example.demo.service.CallbackHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private List<CallbackHandler> callbackHandlers;

    @Value("${vk.confirmation_response}")
    private String confirmationResponse;
    private CallbackHandler confirmationHandler;
    private CallbackHandler messageHandler;

    @BeforeEach
    void setUp() {
        confirmationHandler = mock(CallbackHandler.class);
        when(confirmationHandler.getType()).thenReturn("confirmation");
        when(confirmationHandler.handleCallback(any())).thenReturn("26a48452");

        messageHandler = mock(CallbackHandler.class);
        when(messageHandler.getType()).thenReturn("message_new");
        when(messageHandler.handleCallback(any())).thenReturn("ok");

        when(callbackHandlers.stream()).thenReturn(List.of(confirmationHandler, messageHandler).stream());
    }

    @Test
    void handleCallback_confirmation() throws Exception {
        String confirmationJson = "{\"type\":\"confirmation\",\"group_id\":226379469}";

        mockMvc.perform(post("/VKBot")
                        .contentType("application/json")
                        .content(confirmationJson))
                .andExpect(status().isOk())
                .andExpect(content().string(confirmationResponse));
    }

    @Test
    void handleCallback_messageNew() throws Exception {
        String messageNewJson = "{\"type\":\"message_new\",\"object\":{\"message\":{\"date\":1719682527,\"from_id\":491114957,\"id\":18,\"out\":0,\"text\":\"ghbdtn\",\"peer_id\":491114957}}}";

        mockMvc.perform(post("/VKBot")
                        .contentType("application/json")
                        .content(messageNewJson))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    void handleCallback_noHandler() throws Exception {
        String unknownTypeJson = "{\"type\":\"unknown_type\",\"group_id\":226379469}";

        mockMvc.perform(post("/VKBot")
                        .contentType("application/json")
                        .content(unknownTypeJson))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }
}
