package step.crudappboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import step.crudappboot.dao.RoleRepository;
import step.crudappboot.model.Role;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        if (roleRepository.findByUsername("ROLE_ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setUsername("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByUsername("ROLE_USER") == null) {
            Role userRole = new Role();
            userRole.setUsername("ROLE_USER");
            roleRepository.save(userRole);
        }
    }
}