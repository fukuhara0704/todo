package com.example.todo;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Hash {
    
    public static void main(String[] args) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(encoder.encode("test1"));
        System.out.println(encoder.encode("test2"));
        System.out.println(encoder.encode("test3"));
        System.out.println(encoder.encode("fukuhara"));
    }
}