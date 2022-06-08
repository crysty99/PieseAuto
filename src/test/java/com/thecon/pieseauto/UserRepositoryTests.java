package com.thecon.pieseauto;

import com.thecon.pieseauto.user.User;
import com.thecon.pieseauto.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNewUser(){
        User user = new User();
        user.setName("user");
        user.setPassword("user");
        user.setEmail("usertest222@gmail.com");
        user.setDateOfBirth(new Date(2000,01,01));
        user.setPhoneNumber("0720000000");
        user.setAddress("address test");
        user.setUserRole("user");
        //user.setProfileImage("/poza.png");
        user.setActive(true);

        User savedUser= repo.save(user);

        Assertions.assertNotNull(savedUser);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertNotNull(users);

        for (User user : users){
            System.out.println(users);
        }
    }

    @Test
    public void testUpdate(){
        int idUser = 2;
        Optional<User> optionalUser = repo.findById(idUser);
        User user = optionalUser.get();
        user.setPhoneNumber("000000");
        repo.save(user);

        User updatedUser= repo.findById(idUser).get();
        Assertions.assertEquals(updatedUser.getPhoneNumber(),"000000");
    }

    @Test
    public void testGet(){
        int idUser = 1;
        Optional<User> optionalUser = repo.findById(idUser);
        Assertions.assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get());
    }

    //@Test
    public void testDelete(){
        int idUser = 1;
        repo.deleteById(idUser);

        Optional<User> optionalUser = repo.findById(idUser);
        Assertions.assertFalse(optionalUser.isPresent());
    }
}
