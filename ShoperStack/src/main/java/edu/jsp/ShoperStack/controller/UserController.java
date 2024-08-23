package edu.jsp.ShoperStack.controller;

import edu.jsp.ShoperStack.entity.User;
import edu.jsp.ShoperStack.service.UserService;
import edu.jsp.ShoperStack.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/version/users"  )
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
