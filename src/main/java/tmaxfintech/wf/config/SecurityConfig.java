package tmaxfintech.wf.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import tmaxfintech.wf.config.jwt.JwtAuthenticationFilter;
import tmaxfintech.wf.config.jwt.JwtAuthorizationFilter;
import tmaxfintech.wf.domain.user.repository.UserRepository;
import tmaxfintech.wf.util.jwt.JwtUtility;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    private final JwtUtility jwtUtility;

//    public SecurityConfig(CorsFilter corsFilter, UserRepository userRepository) {
//        this.corsFilter = corsFilter;
//        this.userRepository = userRepository;
//    }

    public SecurityConfig(CorsFilter corsFilter, UserRepository userRepository, ObjectMapper objectMapper, JwtUtility jwtUtility) {
        this.corsFilter = corsFilter;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.jwtUtility = jwtUtility;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(new JwtAuthenticationFilter("/users/login", authenticationManager(), objectMapper, jwtUtility), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository, jwtUtility))
                .authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}
