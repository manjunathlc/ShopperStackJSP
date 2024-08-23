package edu.jsp.ShoperStack.dao;

import edu.jsp.ShoperStack.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao {
    User saveUser(User user);

    Optional<User> findById(Integer userId);

    List<User> findAll();

    void deleteById(Integer userId);

    Boolean isPresent(Integer userId);
}
