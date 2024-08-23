package edu.jsp.ShoperStack.dao.impl;

import edu.jsp.ShoperStack.dao.UserDao;
import edu.jsp.ShoperStack.entity.User;
import edu.jsp.ShoperStack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Boolean isPresent(Integer userId) {
        return userRepository.existsById(userId);
    }
}
