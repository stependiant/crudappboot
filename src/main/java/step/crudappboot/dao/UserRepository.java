package step.crudappboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import step.crudappboot.model.User;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> findAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Transactional
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void deleteById(Long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    @Transactional
    public boolean existsByName(String name) {
        return findByName(name) != null;
    }

    private User findByName(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Transactional
    public List<User> findByNameLike(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Transactional
    public List<User> findByNameStartsWith(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    @Transactional
    public List<User> findByNameEndsWith(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", "%" + name)
                .getResultList();
    }

    @Transactional
    public List<User> findByNameContains(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

}
