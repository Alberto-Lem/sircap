package com.cev.sircapcev.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.entity.BitacorasEntity;
import com.cev.sircapcev.repositories.BitacorasRepository;
import com.cev.sircapcev.security.securityEntity.UserEntity;
import com.cev.sircapcev.security.securityRespository.UserEntityRepository;
@Service
public class BitacorasService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private BitacorasRepository loginHistoryRepository;
    
    public void historial(String username, String actividad) {
        UserEntity user = userEntityRepository.findByUsername(username).orElse(null);
        if (user != null) {
            BitacorasEntity history = new BitacorasEntity();
            history.setUsername(username);
            history.setLoginTime(new Date());
            history.setAccion(actividad);
            history.setSuccessful(true);
            loginHistoryRepository.save(history);
        }
    }
}

