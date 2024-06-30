package com.example.demo.dto;

public class VkMessageObject {

    private VkMessageDetails message;
    private VkClientInfo clientInfo;

    public VkMessageDetails getMessage() {
        return message;
    }

    public void setMessage(VkMessageDetails message) {
        this.message = message;
    }

    public VkClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(VkClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    @Override
    public String toString() {
        return "VkMessageObject{" +
                "message=" + message +
                ", clientInfo=" + clientInfo +
                '}';
    }
}
