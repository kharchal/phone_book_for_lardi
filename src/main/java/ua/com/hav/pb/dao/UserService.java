package ua.com.hav.pb.dao;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yulia on 23.07.2017.
 */
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean foo(String s) {
        return userDao.checkLogin(s);
    }
}
