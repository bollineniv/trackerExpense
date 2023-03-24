package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

     boolean existsByEmail(String email);

     Optional<Users> findByEmail(String email);

     Optional<Users> findByUsername(String username);

}
