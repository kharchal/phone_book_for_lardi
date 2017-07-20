package ua.com.hav.pb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.hav.pb.dao.ContactDao;
import ua.com.hav.pb.dao.UserDao;
import ua.com.hav.pb.dao.sql.ContactDaoSQLImpl;
import ua.com.hav.pb.domain.Contact;
import ua.com.hav.pb.domain.User;
import ua.com.hav.pb.util.Message;
import ua.com.hav.pb.validator.ReflectionValidator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.hav.pb.validator.ReflectionValidator.*;

/**
 * Created by yulia on 12.07.2017.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping({"/", "/login", "/index"})
    public String welcome() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session) {
        System.out.println("user = " + user);
        User loggedUser = userDao.login(user.getLogin(), user.getPassword());
        System.out.println("loggedUser = " + loggedUser);
        if (loggedUser != null) {
            session.setAttribute("loggeduser", loggedUser);
            return "redirect:/contact/";
        }
        session.setAttribute("msg", new Message("You have entered wrong login/password. Try again."));
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }

    @RequestMapping("/regform")
    public String regpage() {
        return "regpage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, Model model, HttpSession session) {

        System.out.println("user = " + user);
        System.out.println(user.getPassword());
        Map<String, String> errors = validate(user);
        System.out.println("errors = " + errors);
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "regpage";
        }
        if (userDao.save(user)) {
            session.setAttribute("msg", new Message("Successful registration! You may login"));
            return "redirect:/index";
        }
        model.addAttribute("msg", new Message("Login is taken"));
        return "regpage";
    }

//    @RequestMapping("/errors")
//    public String error(Model model) {
//        if (true) throw new ArrayIndexOutOfBoundsException("nnnn");
//        model.addAttribute("msg", "Something went wrong...");
//        return "error";
//    }
}
