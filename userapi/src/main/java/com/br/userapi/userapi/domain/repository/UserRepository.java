package com.br.userapi.userapi.domain.repository;

import com.br.userapi.userapi.domain.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
            UserDetails findByEmail(String email);
}
