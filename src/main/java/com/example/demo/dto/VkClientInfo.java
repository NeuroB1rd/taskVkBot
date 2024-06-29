package com.example.demo.dto;

import java.util.List;

public class VkClientInfo {

    private List<String> buttonActions;
    private boolean keyboard;
    private boolean inlineKeyboard;
    private boolean carousel;
    private int langId;

    public List<String> getButtonActions() {
        return buttonActions;
    }

    public void setButtonActions(List<String> buttonActions) {
        this.buttonActions = buttonActions;
    }

    public boolean isKeyboard() {
        return keyboard;
    }

    public void setKeyboard(boolean keyboard) {
        this.keyboard = keyboard;
    }

    public boolean isInlineKeyboard() {
        return inlineKeyboard;
    }

    public void setInlineKeyboard(boolean inlineKeyboard) {
        this.inlineKeyboard = inlineKeyboard;
    }

    public boolean isCarousel() {
        return carousel;
    }

    public void setCarousel(boolean carousel) {
        this.carousel = carousel;
    }

    public int getLangId() {
        return langId;
    }

    public void setLangId(int langId) {
        this.langId = langId;
    }

    @Override
    public String toString() {
        return "VkClientInfo{" +
                "buttonActions=" + buttonActions +
                ", keyboard=" + keyboard +
                ", inlineKeyboard=" + inlineKeyboard +
                ", carousel=" + carousel +
                ", langId=" + langId +
                '}';
    }
}
