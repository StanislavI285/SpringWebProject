package softuni.unisports.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.unisports.interceptors.LogInterceptor;
import softuni.unisports.interceptors.UserDetailsInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;
    private final UserDetailsInterceptor userDetailsInterceptor;

    public InterceptorConfig(LogInterceptor logInterceptor, UserDetailsInterceptor userDetailsInterceptor) {
        this.logInterceptor = logInterceptor;
        this.userDetailsInterceptor = userDetailsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(userDetailsInterceptor);
    }
}
