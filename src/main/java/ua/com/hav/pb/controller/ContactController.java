package ua.com.hav.pb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.hav.pb.dao.ContactDao;
import ua.com.hav.pb.domain.Contact;
import ua.com.hav.pb.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static ua.com.hav.pb.validator.ReflectionValidator.*;

/**
 * Created by sunny on 13.07.2017.
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

	@Autowired
	private ContactDao contactDao;

	@RequestMapping(value = "/")
	public String show(Model model, HttpSession session) {
		model.addAttribute("contacts",
				contactDao.findForUser(((User) session.getAttribute("loggeduser")).getId()));
		return "show";
	}

	@RequestMapping("/new")
    public String newContact(Model model) {
        model.addAttribute("user", new User());
        return "/contform";
    }

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String login(@RequestParam String search, Model model, HttpSession session) {
		model.addAttribute("contacts",
				contactDao.find(search, ((User) session.getAttribute("loggeduser")).getId()));
		return "show";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		contactDao.delete(id, ((User) session.getAttribute("loggeduser")).getId());
		return "redirect:/contact/";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String register(Contact contact, Model model, HttpSession session) {
        Map<String, String> errors = validate(contact);
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "/contform";
        }
		contactDao.save(contact, ((User) session.getAttribute("loggeduser")).getId());
		return "redirect:/contact/";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model, HttpSession session) {
		model.addAttribute("contact",
				contactDao.find(id, ((User) session.getAttribute("loggeduser")).getId()));
		return "/contform";
	}

}