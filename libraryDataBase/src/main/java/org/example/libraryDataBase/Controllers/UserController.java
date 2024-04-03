package org.example.libraryDataBase.Controllers;

import org.example.libraryDataBase.Repositories.UserService;
import org.example.libraryDataBase.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        // Sprawdź, czy istnieje już użytkownik o tym samym loginie
        if (userService.findByUserName(user.getUserName()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already taken");
        }
        // Zapisz nowego użytkownika
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}

