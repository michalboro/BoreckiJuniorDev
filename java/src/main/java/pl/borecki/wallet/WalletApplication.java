package pl.borecki.wallet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.borecki.wallet.appUserService.AppUserService;
import pl.borecki.wallet.category.CategoryRepository;
import pl.borecki.wallet.model.AppUser;
import pl.borecki.wallet.model.Role;

import java.util.ArrayList;

@SpringBootApplication
public class WalletApplication {
	public static void main(String[] args) {
//		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(WalletApplication.class, args);
//		CategoryRepository categoryRepository = configurableApplicationContext.getBean(CategoryRepository.class);

//		Category category = new Category("rachunki");
//		Transaction rata = new Transaction("ca", 34234, "income",category);
//		List<Transaction> transactions = Arrays.asList(rata);
//		category.setTransactions(transactions);
//		categoryRepository.save(category);

	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(AppUserService appUserService) {
		return args -> {

//			appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
//			appUserService.saveRole(new Role(null, "ROLE_MANAGER"));
//			appUserService.saveRole(new Role(null, "ROLE_USER"));

//			appUserService.saveUser(new AppUser(null, "adam kowalski", "janek", "1234", new ArrayList<>()));
//
//			appUserService.addRoleToUser("janek", "ROLE_USER");
//			appUserService.addRoleToUser("janek", "ROLE_ADMIN");
//			appUserService.addRoleToUser("janek", "ROLE_MANAGER");
		};

	}
}


