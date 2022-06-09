package com.thecon.pieseauto.user;

import com.thecon.pieseauto.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Value("${uploadDir}")
    private String uploadFolder;
    @Autowired private UserService service;

    //private final Logger log = LoggerFactory.getLogger(this.getClass());

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

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","User saved successfully!");
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{idUser}")
    public String deleteUser(@PathVariable("idUser") int id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "User (ID: "+id+") deleted successfully!");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/users/displayImage/{idUser}")
    @ResponseBody
    void showImage(@PathVariable("idUser") int id, HttpServletResponse response)
            throws IOException, UserNotFoundException {
        User user = service.get(id);
        response.setContentType("image/png");
        response.getOutputStream().write(user.getProfileImage());
        response.getOutputStream().close();
    }
}
