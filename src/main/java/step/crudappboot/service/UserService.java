package step.crudappboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import step.crudappboot.dao.UserRepository;
import step.crudappboot.model.Role;
import step.crudappboot.model.User;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Transactional
    public List<User> getAllUsers() {
        try {
            log.info("Fetching all users");
            List<User> users = userRepository.findAll();
            log.info("Found {} users", users.size());
            return users;
        } catch (NullPointerException e) {
            log.debug("Null users ", e);
            return null;
        }
    }

    @Transactional
    public User getUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        User user = userRepository.findById(id);
        if (user == null) {
            log.warn("User with id {} not found", id);
        } else {
            log.info("User with id {} found: {}", id, user);
        }
        return user;
    }

    @Transactional
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void register(User user) {
        log.info("Registering user: {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.findByName("ROLE_ADMIN");
        if (userRole == null) {
            throw new RuntimeException("Role ROLE_ADMIN not found");
        }

        user.setRoles(Collections.singleton(userRole));
        log.info("Save password in db");
        userRepository.save(user);
        log.info("User registered: {}", user);
    }

    @Transactional
    public void save(User user) {
        log.info("Saving user: {}", user);
        userRepository.save(user);
        log.info("User saved: {}", user);
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
        log.info("User with id {} deleted", id);
    }

    @Transactional
    public void update(User user, Long id) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameAndFetchLazyRelationEagerly(username);
        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
