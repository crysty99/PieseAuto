package com.thecon.pieseauto.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class ProductController {
    @Autowired private ProductService service;

    @GetMapping("/products")
    public String showProductList(Model model){
        ArrayList<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }
}
