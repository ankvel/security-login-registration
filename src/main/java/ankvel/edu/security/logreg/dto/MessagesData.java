package ankvel.edu.security.logreg.dto;

import java.util.List;

import static java.util.Collections.emptyList;

public class MessagesData {

    public enum Type {
        INFO,
        WARN,
        ERROR
    }

    private final Type type;

    private final String message;

    private final List<String> messages;

    public MessagesData(Type type, String message) {
        this.type = type;
        this.message = message;
        this.messages = emptyList();
    }

    public MessagesData(Type type, List<String> messages) {
        this.type = type;
        this.message = null;
        this.messages = messages;
    }

    public MessagesData(Type type, String message, List<String> messages) {
        this.type = type;
        this.message = message;
        this.messages = messages;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getMessages() {
        return messages;
    }
}
