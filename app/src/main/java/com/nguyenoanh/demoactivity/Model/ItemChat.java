package com.nguyenoanh.demoactivity.Model;

public class ItemChat {
    private int sender;
    private int receiver;
    private String message;

    public ItemChat(int sender, int receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public ItemChat() {
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
