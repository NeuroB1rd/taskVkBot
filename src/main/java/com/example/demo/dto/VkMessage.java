package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VkMessage {

    private String type;
    private VkMessageObject object;
    private String secret;
    private String groupId;

    @JsonProperty("group_id")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VkMessageObject getObject() {
        return object;
    }

    public void setObject(VkMessageObject object) {
        this.object = object;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "VkMessage{" +
                "type='" + type + '\'' +
                ", object=" + object +
                ", secret='" + secret + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
