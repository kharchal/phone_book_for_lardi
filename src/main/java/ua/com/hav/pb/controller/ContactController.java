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
import java.util.List;
import java.util.Map;

import static ua.com.hav.pb.validator.ReflectionValidator.*;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

	@Autowired
	private ContactDao contactDao;

	@RequestMapping(value = "/")
	public String show(Model model, HttpSession session) {
		System.out.println("/contact/");
        User user = (User) session.getAttribute("loggeduser");
		Long id = user.getId();
		List<Contact> contacts = contactDao.findForUser(id);
		model.addAttribute("contacts", contacts);
		return "show";
	}

	@RequestMapping("/new")
    public String newContact(Model model) {
        model.addAttribute("user", new User());
        return "/contform";
    }

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String login(@RequestParam String search, Model model, HttpSession session) {
		System.out.println("/contact/search" + search);
//		String str = parse(contact);
        if (search == null) {
            System.out.println("NULL");
        }
        if ("".equals(search)) {
            System.out.println("\"\"");
//            search = "%";
        }
        User user = (User) session.getAttribute("loggeduser");
		List<Contact> contacts = contactDao.find(search, user.getId());
		model.addAttribute("contacts", contacts);
		return "show";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, HttpSession session) {
		System.out.println("contact/delete/" + id);
        User user = (User) session.getAttribute("loggeduser");
		contactDao.delete(id, user.getId());
		return "redirect:/contact/";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String register(Contact contact, Model model, HttpSession session) {
		System.out.println("/contact/save" + contact);
        Map<String, String> errors = validate(contact);
        System.out.println("errors = " + errors);
        if (errors.size() > 0) {
            model.addAttribute("errors", errors);
            return "contform";
        }
        User user = (User) session.getAttribute("loggeduser");
		contactDao.save(contact, user.getId());

		return "redirect:/contact/";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model, HttpSession session) {
		System.out.println("/contact/edit/" + id);
		User user = (User) session.getAttribute("loggeduser");
		Contact contact = contactDao.find(id, user.getId());
		model.addAttribute("contact", contact);
		return "/contform";
	}


}