package com.thecon.pieseauto.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired private UserRepository repo;

    public ArrayList<User> listAll() {
        return (ArrayList<User>) repo.findAll();
    }
}
