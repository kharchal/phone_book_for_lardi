package ua.com.hav.pb.util;

/**
 * Created by sunny on 19.07.2017.
 */
public class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    private String getMessage() {
        String s = message;
        message = "";
        return s;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
