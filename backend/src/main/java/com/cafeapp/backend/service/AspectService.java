package com.cafeapp.backend.service;

import org.springframework.stereotype.Service;

@Service
public class AspectService {

    public String getUserById(Long id) {
        return "Kullanıcı ID: " + id;
    }

    public void createUser(String name) {
        System.out.println("Kullanıcı oluşturuldu: " + name);
    }
}
