package pl.borecki.wallet.appUserService;

import pl.borecki.wallet.model.AppUser;
import pl.borecki.wallet.model.Role;

import java.util.List;

public interface AppUserService {

    AppUser saveUser(AppUser appuser);

    Role saveRole (Role role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUser();
}


