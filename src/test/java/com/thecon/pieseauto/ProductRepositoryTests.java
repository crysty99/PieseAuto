package com.thecon.pieseauto;

import com.thecon.pieseauto.product.Product;
import com.thecon.pieseauto.product.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired private ProductRepository repo;

    @Test
    public void testAddNewProduct(){
        Product product = new Product();
        product.setProductName("piesaTest2");
        product.setProductDescription("despre piesa test2");
        product.setStock(1);

        Product savedProduct = repo.save(product);

        Assertions.assertNotNull(savedProduct);
    }

    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertNotNull(products);

        for (Product product : products){
            System.out.println(product);
        }
    }

    @Test
    public void testUpdate(){
        int idPiesa = 1;
        Optional<Product> optionalProduct = repo.findById(idPiesa);
        Product product = optionalProduct.get();
        product.setProductName("PiesaUpdateTest");
        repo.save(product);

        Product updatedProduct = repo.findById(idPiesa).get();
        Assertions.assertEquals(updatedProduct.getProductName(),"PiesaUpdateTest");
    }

    @Test
    public void testGet(){
        int idPiesa = 1;
        Optional<Product> optionalProduct = repo.findById(idPiesa);
        Assertions.assertTrue(optionalProduct.isPresent());
        System.out.println(optionalProduct.get());
    }

    @Test
    public void testDelete(){
        int idPiesa = 1;
        repo.deleteById(idPiesa);

        Optional<Product> optionalProduct = repo.findById(idPiesa);
        Assertions.assertFalse(optionalProduct.isPresent());
    }
}
