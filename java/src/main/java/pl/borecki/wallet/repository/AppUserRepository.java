package pl.borecki.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.borecki.wallet.model.AppUser;

import javax.persistence.Entity;
@RequestMapping
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}

