package edu.jsp.ShoperStack.repository;

import edu.jsp.ShoperStack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //To find user details by email and password
    Optional<User> findByUserEmailAndUserPassword(String userEmail,String userPassword);

    //to find user details by email
    Optional<User> findByUserEmail(String userEmail);
}
