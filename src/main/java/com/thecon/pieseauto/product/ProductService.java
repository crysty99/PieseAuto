package com.thecon.pieseauto.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired private ProductRepository repo;

    public ArrayList<Product> listAll() {
        return (ArrayList<Product>) repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(int id) throws ProductNotFoundException{
        Optional<Product> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new ProductNotFoundException("No products with id " + id);
    }
}
