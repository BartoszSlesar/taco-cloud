package com.bard.spring.services;

import com.bard.spring.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException;
    User saveUser(User user);
}
