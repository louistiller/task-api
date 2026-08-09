package de.louis.task_api.service;

import de.louis.task_api.model.User;
import de.louis.task_api.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User(username, hashedPassword);
        return userRepository.save(user);

    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User nicht gefunden"));
    }

}
