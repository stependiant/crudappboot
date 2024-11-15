package step.crudappboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import step.crudappboot.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByUsername(String name);
}
