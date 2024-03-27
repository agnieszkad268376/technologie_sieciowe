package org.example.libraryDataBase.Controllers;

import org.example.libraryDataBase.Repositories.UserService;
import org.example.libraryDataBase.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userRepository, PasswordEncoder passwordEncoder){
        this.userService = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User addUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Iterable<User> getAllUser(){
        return userService.findAll();
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable Integer userId){
        userService.deleteById(userId);
    }

}

