package com.thecon.pieseauto;

import com.thecon.pieseauto.product.Product;
import com.thecon.pieseauto.product.ProductCRUD;
import com.thecon.pieseauto.user.User;
import com.thecon.pieseauto.user.UserCRUD;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired private ProductCRUD repo;

    @Test
    public void testAddNewProduct(){
        Product product = new Product();
        product.setProductName("piesaTest2");
        product.setProductDescription("despre piesa test2");
        product.setStock(1);

        Product savedProduct = repo.save(product);

        Assertions.assertNotNull(savedProduct);
    }
}
