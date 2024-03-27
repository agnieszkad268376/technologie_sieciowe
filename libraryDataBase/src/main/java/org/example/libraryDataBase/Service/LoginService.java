package org.example.libraryDataBase.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.libraryDataBase.LoginForm;
import org.example.libraryDataBase.Repositories.UserService;
import org.example.libraryDataBase.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    private UserService userRepository;
    //kontruktor z Autowired
    private PasswordEncoder passwordEncoder;


    private String key;

    @Autowired
    public LoginService(PasswordEncoder passwordEncoder, UserService userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    public String userLogin(LoginForm loginForm){
        User user = userRepository.findByUserName(loginForm.getLogin());

        if(passwordEncoder.matches(loginForm.getPassword()
                ,user.getPassword())){
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 40 * 60* 100))
                    .claim("id", user.getUserId())
                    .claim("role", user.getRole())
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }
}
