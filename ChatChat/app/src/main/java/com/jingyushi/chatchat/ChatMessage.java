package com.jingyushi.chatchat;

/**
 * Created by jingyushi on 8/22/17.
 */

public class ChatMessage {
    String name;
    String message;

    ChatMessage(){};
    ChatMessage(String name, String message){
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
