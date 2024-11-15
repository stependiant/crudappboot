package step.crudappboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import step.crudappboot.model.User;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("SELECT User", User.class).getResultList();
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public void delete(long id) {
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public boolean existsById(long id) {
        return findById(id) != null;
    }

    public boolean existsByName(String name) {
        return findByName(name) != null;
    }

    public User findByName(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<User> findByNameLike(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    public List<User> findByNameStartsWith(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", name + "%")
                .getResultList();
    }

    public List<User> findByNameEndsWith(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", "%" + name)
                .getResultList();
    }

    public List<User> findByNameContains(String name) {
        return entityManager.createQuery("SELECT User FROM User WHERE name LIKE :name", User.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }



}
