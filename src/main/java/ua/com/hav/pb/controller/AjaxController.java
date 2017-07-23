package ua.com.hav.pb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.hav.pb.dao.UserDao;

import javax.sql.DataSource;

/**
 * Created by sunny on 21.07.2017.
 */
@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    @ResponseBody
    public boolean ajax(@RequestParam String log) {
        return userDao.checkLogin(log);
    }

}
