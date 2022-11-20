package org.agafvic.chargepoints.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles(Role.ADMIN)
                .and()
                .withUser("user").password("user").roles(Role.CUSTOMER)
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/version").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/v1/admin/**").hasAnyRole(Role.ADMIN)
                .antMatchers("/api/v1/customer/**").hasAnyRole(Role.CUSTOMER)
                .and()
                .httpBasic().and()
                .headers().frameOptions().disable();
    }
}
