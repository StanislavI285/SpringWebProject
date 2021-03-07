package softuni.unisports.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                authorizeRequests().
                antMatchers("/js/**", "/css/**", "/img/**").permitAll().  //<----- статичните ресурси са видими от всеки
                antMatchers("/", "/users/login", "/users/register").permitAll(). //<----- страници, които са видими от всеки
                    and().
                formLogin()
                .loginPage("/users/login").
                usernameParameter("username"). //<----- като параметър е името на полето от HTML формата
                passwordParameter("password"). //<----- като параметър името на полето от HTML формата
                defaultSuccessUrl("/index"). // <----- къде редиректваме след успешен login
                failureForwardUrl("/users/login-error"); //<----- къде редиректваме след неуспешен login

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
    }
}
