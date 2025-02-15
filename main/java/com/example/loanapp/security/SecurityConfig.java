package com.example.loanapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

   // @Autowired
    private JwtRequestFilter jwtRequestFilter;

    //@Override
    //protected void configure(HttpSecurity http) throws Exception {
       // http.csrf().disable()
            //    .authorizeRequests()
             //   .antMatchers("/api/user/**").permitAll() // Allow user registration
               // .anyRequest().authenticated()
               // .and()
               // .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
   // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   // @Override
 //   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
   // }

   // @Bean
   // @Override
  // public AuthenticationManager authenticationManagerBean() throws Exception {
     //   return super.authenticationManagerBean();
   // }
}