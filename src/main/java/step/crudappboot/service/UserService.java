package step.crudappboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import step.crudappboot.dao.UserRepository;
import step.crudappboot.model.User;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        log.info("UserService initialized with UserRepository: {}", userRepository);
    }

    public List<User> getAllUsers() {
        try {
            log.info("Fetching all users");
            List<User> users = userRepository.findAllUsers();
            log.info("Found {} users", users.size());
            return users;
        } catch (NullPointerException e) {
            log.debug("Error fetching all users", e);
            return null;
        }
    }

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

    public void save(User user) {
        log.info("Saving user: {}", user);
        userRepository.save(user);
        log.info("User saved: {}", user);
    }

    public void deleteById(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
        log.info("User with id {} deleted", id);
    }

    public void update(User user) {
        log.info("Updating user: {}", user);
        userRepository.update(user);
        log.info("User updated: {}", user);
    }

    public boolean existsById(Long id) {
        log.info("Checking if user with id {} exists", id);
        boolean exists = userRepository.existsById(id);
        log.info("User with id {} exists: {}", id, exists);
        return exists;
    }

    public boolean existsByName(String name) {
        log.info("Checking if user with name {} exists", name);
        boolean exists = userRepository.existsByName(name);
        log.info("User with name {} exists: {}", name, exists);
        return exists;
    }

    public List<User> getByNameLike(String name) {
        log.info("Fetching users with name like: {}", name);
        List<User> users = userRepository.findByNameLike(name);
        log.info("Found {} users with name like: {}", users.size(), name);
        return users;
    }

    public List<User> getByNameStartsWith(String name) {
        log.info("Fetching users with name starting with: {}", name);
        List<User> users = userRepository.findByNameStartsWith(name);
        log.info("Found {} users with name starting with: {}", users.size(), name);
        return users;
    }

    public List<User> getByNameEndsWith(String name) {
        log.info("Fetching users with name ending with: {}", name);
        List<User> users = userRepository.findByNameEndsWith(name);
        log.info("Found {} users with name ending with: {}", users.size(), name);
        return users;
    }

    public List<User> getByNameContains(String name) {
        log.info("Fetching users with name containing: {}", name);
        List<User> users = userRepository.findByNameContains(name);
        log.info("Found {} users with name containing: {}", users.size(), name);
        return users;
    }
}
