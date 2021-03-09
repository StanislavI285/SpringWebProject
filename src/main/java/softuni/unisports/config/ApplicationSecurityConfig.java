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
                csrf().disable(). //TODO check why not working without this line
                authorizeRequests().
                antMatchers("/js/**", "/css/**", "/img/**").permitAll().  //<----- статичните ресурси са видими от всеки
                anyRequest().permitAll().
                and().
                formLogin().
                loginPage("/users/login"). //<----- като параметър името на полето от HTML формата
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/travel"). // <----- къде редиректваме след успешен login
                failureUrl("/users/login-error"); //<----- къде редиректваме след неуспешен login

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);
    }
}
