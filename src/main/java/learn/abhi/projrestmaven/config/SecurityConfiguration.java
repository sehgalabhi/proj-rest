package learn.abhi.projrestmaven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((authorize) ->
                        authorize
                                .mvcMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                                .mvcMatchers(HttpMethod.POST, "/basicAuthCars").hasRole("CARUSER")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf().disable();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        final UserDetails user = User.withDefaultPasswordEncoder()
                .username("abhi")
                .password("password")
                .roles("CARUSER")
                .build();

        final UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("nidhi")
                .password("password")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, userAdmin);

    }


}
