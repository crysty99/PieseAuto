package com.thecon.pieseauto.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    public Long countByIdUser(Integer id);
    public Long getUserByNameAndEmail(String name, String email);
    public Long deleteUserByIdUser(Integer id);

}
