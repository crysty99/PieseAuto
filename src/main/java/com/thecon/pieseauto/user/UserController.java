package com.thecon.pieseauto.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class UserController {

    @Value("${uploadDir}")
    private String uploadFolder;
    @Autowired
    private UserService service;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/users")
    public String showUserList(Model model){
        ArrayList<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new") //not used yet
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("title", "Add new user");
        return "userForm";
    }

    @GetMapping("/users/edit/{idUser}")
    public String showEditForm(@PathVariable("idUser") int id, Model model, RedirectAttributes ra){
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("title", "Edit user (ID: "+ id +")");
            return "userForm";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{idUser}")
    public String deleteUser(@PathVariable("idUser") int id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "User (ID: "+id+") deleted succesfully!");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }
}
