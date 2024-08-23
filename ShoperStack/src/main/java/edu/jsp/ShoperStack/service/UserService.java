package edu.jsp.ShoperStack.service;

import edu.jsp.ShoperStack.dao.UserDao;
import edu.jsp.ShoperStack.entity.User;
import edu.jsp.ShoperStack.exception.UserNotFoundException;
import edu.jsp.ShoperStack.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword())); // encode password before saving it to DB
        ResponseStructure<User> structure = new ResponseStructure<>();
        structure.setData(userDao.saveUser(user));
        structure.setMessage("User saved successfully");
        structure.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(structure);
    }

    public ResponseEntity<ResponseStructure<User>> findById(Integer userId) {
        Optional<User> optional = userDao.findById(userId);
        if (optional.isPresent()) {
            ResponseStructure<User> structure = new ResponseStructure<>();
            structure.setData(optional.get());
            structure.setMessage("User found successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(structure);
        }
        throw new UserNotFoundException("User not found with id = " + userId);
    }

    public ResponseEntity<ResponseStructure<List<User>>> findAll() {
        ResponseStructure<List<User>> structure = new ResponseStructure<>();
        structure.setData(userDao.findAll());
        structure.setMessage("All users found successfully");
        structure.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(structure);
    }

    public ResponseEntity<ResponseStructure<String>> deleteById(Integer userId) {
        if (userDao.isPresent(userId)) {
            userDao.deleteById(userId);
            ResponseStructure<String> structure = new ResponseStructure<>();
            structure.setMessage("User deleted successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(structure);
        }
        throw new UserNotFoundException("User not found with id = " + userId);

    }

}
