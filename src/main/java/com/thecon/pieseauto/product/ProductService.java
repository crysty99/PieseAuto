package com.thecon.pieseauto.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired private ProductRepository repo;

    public ArrayList<Product> listAll() {
        return (ArrayList<Product>) repo.findAll();
    }
}
