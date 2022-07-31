package com.sktech.academicportal.helper;

public class Message {
    private String content;
    private String type;
    public Message(String content, String type) {
        super();
        this.content = content;
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public String getType() {
        return type;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setType(String type) {
        this.type = type;
    }



}
