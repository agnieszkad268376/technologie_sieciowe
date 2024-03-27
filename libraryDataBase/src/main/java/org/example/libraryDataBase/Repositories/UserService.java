package org.example.libraryDataBase.Repositories;

import org.example.libraryDataBase.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends JpaRepository<User, Integer> {

    //User findByUserName(String userName);
    @Query(value = "SELECT u FROM User u WHERE u.userName = :loginForm")
    User findByUserName(String loginForm);


}

