package com.thecon.pieseauto.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/products/new")
    public String showNewForm(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("title", "Add new product");
        return "productForm";
    }

    @RequestMapping("/products/save")
    public String saveProduct(Product product, RedirectAttributes ra){
        service.save(product);
        ra.addFlashAttribute("message","Product saved successfully!");
        return "redirect:/products";
    }

    @RequestMapping("/products/edit/{idPiesa}")
    public String showEditForm(@PathVariable("idPiesa") int id, Model model, RedirectAttributes ra){
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);
            model.addAttribute("title", "Edit product (ID: "+ id +")");
            return "productForm";
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/products";
        }
    }

    @RequestMapping("/products/delete/{idPiesa}")
    public String deleteProduct(@PathVariable("idPiesa") int id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Product (ID: "+id+") deleted successfully!");
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/products";
    }

}
