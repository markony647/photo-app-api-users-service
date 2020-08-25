package com.appsdeveloperblog.photoapp.api.users.users.security;

import com.appsdeveloperblog.photoapp.api.users.users.service.UsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Value(value = "${gateway.ip}")
    private String ip;

    private UsersService usersService;
    private BCryptPasswordEncoder passwordEncoder;
    private Environment env;

    public WebSecurity(UsersService usersService, BCryptPasswordEncoder passwordEncoder, Environment env) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable(); // needed to make h2 console to work
        http.authorizeRequests().antMatchers("/**").hasIpAddress(ip)
                .and()
                .addFilter(getAuthenticationFilter());
    }

    private Filter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter =  new AuthenticationFilter(usersService, env, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder);
    }
}
