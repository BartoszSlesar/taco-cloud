package com.bard.spring.services;

import com.bard.spring.domain.User;
import com.bard.spring.repositories.crud.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        return optionalUser
                .orElseThrow(() -> new UsernameNotFoundException("User with userName= "+userName + "was not found" ));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
