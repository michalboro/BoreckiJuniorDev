package pl.borecki.wallet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.borecki.wallet.filter.CustomAuthenticationFilter;
import pl.borecki.wallet.filter.CustomAuthorizationFilter;

import javax.persistence.GeneratedValue;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // reszta w UserServicImplementation
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //reszta w WalletApplication

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login"); //zmiana adresu url bo samo login wykorzystywane jest przez CustomAuthenticationFilter
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(GET, "/api/login/**", "/api/token/refresh/**").permitAll();
//      http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER"); //DOPIERO JAK W BAZIE DANYCH USER BEDZIE MIAL PRZYPISANA ROLE
//      http.authorizeRequests().antMatchers(GET, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN"); // TO SAMO CO WYZEJ
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception{
        return super.authenticationManagerBean();
    }
}
