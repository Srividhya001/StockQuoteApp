package com.stockQuoteApplication.service;

import com.stockQuoteApplication.model.MyUserDetails;
import com.stockQuoteApplication.model.User;
import com.stockQuoteApplication.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDetailsRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found" + username));
        //conversion
        return user.map(MyUserDetails::new).get();
    }

    public User registerUser(User user) {

        return userRepo.save(user);
    }
}
