package step.crudappboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import step.crudappboot.dao.UserRepository;
import step.crudappboot.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public List<User> getByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    public List<User> getByNameStartsWith(String name) {
        return userRepository.findByNameStartsWith(name);
    }

    public List<User> getByNameEndsWith(String name) {
        return userRepository.findByNameEndsWith(name);
    }

    public List<User> getByNameContains(String name) {
        return userRepository.findByNameContains(name);
    }

}
