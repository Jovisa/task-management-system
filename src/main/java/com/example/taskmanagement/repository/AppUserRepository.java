package com.example.taskmanagement.repository;

import com.example.taskmanagement.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

}
