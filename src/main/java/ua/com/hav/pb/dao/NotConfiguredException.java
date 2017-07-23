package ua.com.hav.pb.dao;

/**
 * Created by sunny on 16.07.2017.
 */
public class NotConfiguredException extends RuntimeException {
    public NotConfiguredException() {
        super("Configuration is not set up.");
    }
}
