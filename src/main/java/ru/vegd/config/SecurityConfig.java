package ru.vegd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERS_BY_USERNAME_QUERY =
            "SELECT LOGIN AS username, HASH_PASSWORD AS password, enabled " +
                    "FROM public.users WHERE LOGIN = ?";
    private static final String AUTHORITIES_BY_USERNAME_QUERY  =
            "SELECT LOGIN AS username, ROLE_NAME AS authority " +
                    "FROM public.users u " +
                    "JOIN public.roles r ON r.ROLE_ID=u.ROLE_ID " +
                    "WHERE u.LOGIN = ?";

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(USERS_BY_USERNAME_QUERY)
                .authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/addUser").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers(HttpMethod.GET,"/news/**").permitAll()
                .antMatchers("/settings/**").permitAll()
                .antMatchers("/news/create").hasAnyAuthority("ROLE_MOD", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                .antMatchers("/news/addNews").hasAnyAuthority("ROLE_MOD", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                .antMatchers("/news/delete").hasAnyAuthority("ROLE_MOD", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                .antMatchers("/admin/**").hasAnyAuthority("ROLE_MOD", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                .antMatchers("user/**").authenticated()
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "users/**").hasAnyAuthority("ROLE_MOD", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.formLogin()
                .loginPage("/login")
                /*.defaultSuccessUrl("/")*/
                .permitAll();

        http.logout()
                .permitAll()
                .invalidateHttpSession(true);

    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
