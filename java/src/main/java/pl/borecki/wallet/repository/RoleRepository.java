package pl.borecki.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.borecki.wallet.model.Role;

import javax.accessibility.AccessibleRole;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (String name);
}
