package com.thecon.pieseauto.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    public Long countByIdUser(Integer id);
    public Long findByNameAndEmail(String name, String email);
}
