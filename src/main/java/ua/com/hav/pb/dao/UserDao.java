package ua.com.hav.pb.dao;

import ua.com.hav.pb.domain.User;

/**
 * Created by yulia on 15.07.2017.
 */
public interface UserDao {
    User login(String login, String password);
    boolean save (User user);
}
