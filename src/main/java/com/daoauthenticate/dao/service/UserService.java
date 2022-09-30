package com.daoauthenticate.dao.service;

import com.daoauthenticate.dao.model.UserDetailsImpl;
import com.daoauthenticate.dao.model.UserModel;
import com.daoauthenticate.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{


    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> optionalUserModel = userRepository.findByEmail(username);

        if(optionalUserModel.isEmpty()){
            throw  new UsernameNotFoundException("User with Email does not exist");
        }

        return new UserDetailsImpl(optionalUserModel.get().getId(),optionalUserModel.get().getUsername(), optionalUserModel.get().getPassword(),optionalUserModel.get().getEmail());
    }



    public void registerUser(UserModel userModel){
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
    }


}
