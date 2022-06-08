package com.thecon.pieseauto.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/products/new")
    public String showNewForm(Model model){
        model.addAttribute("product",new Product());
        return "productForm";
    }

    @PostMapping("/products/save")
    public String addProduct(Product product, RedirectAttributes ra){
        service.addProduct(product);
        ra.addFlashAttribute("message","Product saved succesfully!");
        return "redirect:/products";
    }
}
