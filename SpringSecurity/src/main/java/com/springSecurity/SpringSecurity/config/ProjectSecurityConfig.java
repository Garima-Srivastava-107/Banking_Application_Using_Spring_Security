package com.springSecurity.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {
    /**
     *  Below is the custom security configurations
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable()).authorizeHttpRequests((requests) -> requests
            .requestMatchers("/account","/balance","/loans","/cards").authenticated()
            .requestMatchers("/notices","/contact","/register").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

/**          Configuration to deny all the requests
 */
/*        http.authorizeHttpRequests(request->request.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
*/
/**           Configuration to permit all the requests
  */
/*
        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
*/

        return http.build();
    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//     /*   UserDetails admin= User.withDefaultPasswordEncoder()
//                .username("garimaa")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user= User.withDefaultPasswordEncoder()
//                .username("vaibhav")
//                .password("123456")
//                .authorities("user")
//                .build();  */
//        UserDetails admin= User
//                .withUsername("garimaa")
//                .password("12345")
//                .authorities("admin")
//                .build();
//        UserDetails user= User
//                .withUsername("vaibhav")
//                .password("123456")
//                .authorities("user")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
//    }
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
