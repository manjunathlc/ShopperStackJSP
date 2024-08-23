package edu.jsp.ShoperStack.configuration.secutriy;

import edu.jsp.ShoperStack.entity.User;
import edu.jsp.ShoperStack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return org.springframework.security.core.userdetails.User.builder().username(user.getUserEmail())
                .password(user.getUserPassword())
                .roles(user.getRole())
               .build();
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}
