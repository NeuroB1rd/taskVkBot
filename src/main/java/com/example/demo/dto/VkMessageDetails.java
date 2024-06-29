package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VkMessageDetails {

    private int date;
    private int fromId;
    private int id;
    private int out;
    private int version;
    private String text;
    private int peerId;
    private int randomId;

    @JsonProperty("date")
    public int getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(int date) {
        this.date = date;
    }

    @JsonProperty("from_id")
    public int getFromId() {
        return fromId;
    }

    @JsonProperty("from_id")
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("out")
    public int getOut() {
        return out;
    }

    @JsonProperty("out")
    public void setOut(int out) {
        this.out = out;
    }

    @JsonProperty("version")
    public int getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(int version) {
        this.version = version;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("peer_id")
    public int getPeerId() {
        return peerId;
    }

    @JsonProperty("peer_id")
    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }

    @JsonProperty("random_id")
    public int getRandomId() {
        return randomId;
    }

    @JsonProperty("random_id")
    public void setRandomId(int randomId) {
        this.randomId = randomId;
    }

    @Override
    public String toString() {
        return "VkMessageDetails{" +
                "date=" + date +
                ", fromId=" + fromId +
                ", id=" + id +
                ", out=" + out +
                ", version=" + version +
                ", text='" + text + '\'' +
                ", peerId=" + peerId +
                ", randomId=" + randomId +
                '}';
    }
}
