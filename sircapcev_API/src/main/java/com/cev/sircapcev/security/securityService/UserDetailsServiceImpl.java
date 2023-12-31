package com.cev.sircapcev.security.securityService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.security.securityEntity.UserEntity;
import com.cev.sircapcev.security.securityRespository.UserEntityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userEntityRepository.findByUsernameOrEmail(username, username);
        if(!userEntity.isPresent())
            throw new UsernameNotFoundException("username not exists");
        
        return UserPrincipal.build(userEntity.get());
    }

}
