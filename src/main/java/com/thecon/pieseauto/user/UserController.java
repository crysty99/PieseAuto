package com.thecon.pieseauto.user;

import com.thecon.pieseauto.product.Product;
import com.thecon.pieseauto.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        ArrayList<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
}
