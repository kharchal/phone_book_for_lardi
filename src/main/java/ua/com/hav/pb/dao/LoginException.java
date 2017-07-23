package ua.com.hav.pb.dao;

/**
 * Created by sunny on 14.07.2017.
 */
public class LoginException extends RuntimeException {
    public LoginException() {
        super("Login process is not passed");
    }
}
