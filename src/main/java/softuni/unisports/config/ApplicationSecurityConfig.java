package softuni.unisports.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import softuni.unisports.security.UniSportsUserDetailsService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UniSportsUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(UniSportsUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    //в този метод настройваме достъпа до ресурсите
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
//                csrf().disable(). //in html must be th:action instead of action, in order to have csrf token and the login to work properly
        authorizeRequests().
                antMatchers("/js/**", "/css/**", "/img/**").permitAll().  //<----- статичните ресурси са видими от всеки
                antMatchers("/admin-panel", "/roles", "/users/list").hasAnyAuthority("ROLE_ADMIN"). //<----- само админ има достъп до админ панел
                antMatchers("/news/add", "/moderator-panel").hasAuthority("ROLE_MODERATOR"). //<----- админ и модератор могат да добавят новини
                anyRequest().permitAll().
                and().
                formLogin().
                        loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // <----- къде редиректваме след успешен login
                        defaultSuccessUrl("/").
                //<----- къде редиректваме след неуспешен login
                        failureForwardUrl("/users/login-error").
                and().
                    logout().
                        //кой endpoint извършва logout, e.g. http://localhost:8080/logout (Must be a POST Request!!!!!!)
                        logoutUrl("/logout").
                        logoutSuccessUrl("/").
                        invalidateHttpSession(true).
                        deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
    }
}
