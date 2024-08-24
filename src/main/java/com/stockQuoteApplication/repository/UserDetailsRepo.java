package com.stockQuoteApplication.repository;


import com.stockQuoteApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);

}
