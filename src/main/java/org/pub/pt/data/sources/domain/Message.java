package org.pub.pt.data.sources.domain;

/**
 * Created by balhau on 10/25/15.
 */
public class Message {
    private final String message;
    private final String source;

    public Message(String message, String source){
        this.message = message;this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public String getSource() {
        return source;
    }

    public String toString(){
        return String.format("{message=%s,source=%s}", message, source);
    }
}
