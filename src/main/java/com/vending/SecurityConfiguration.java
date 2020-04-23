package com.vending;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@EnableWebSecurity
//@Configuration
@Component
public class SecurityConfiguration  {
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();




//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "SELECT username, password, true from users where username = ?");

                ;
   // }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//      http.authorizeRequests().and().formLogin().loginPage("/api/login").permitAll().and().csrf().disable();
//
//
//

//    }
}

