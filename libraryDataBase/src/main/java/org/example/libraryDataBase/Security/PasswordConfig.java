package org.example.libraryDataBase.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    // zakodowanie hasła
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
