package learn.abhi.projrestmaven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicSecurityConfiguration {

    @Bean
    public SecurityFilterChain basicSecurityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests((authorize) ->
                        authorize
                                .mvcMatchers(HttpMethod.POST, "/admin/basic/**").hasRole("ADMIN")
                                .mvcMatchers(HttpMethod.POST, "/basic/**").hasRole("CARUSER")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                //todo what is below csrf
                .csrf((csrf) -> csrf.ignoringAntMatchers("/basic/**", "/admin/basic/**"))
        ;
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager basicUserDetailsService() {
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
