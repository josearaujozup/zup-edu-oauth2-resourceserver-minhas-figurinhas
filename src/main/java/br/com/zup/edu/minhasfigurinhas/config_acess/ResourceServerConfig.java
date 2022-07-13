package br.com.zup.edu.minhasfigurinhas.config_acess;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .formLogin().disable()
                .logout().disable()
                .headers().frameOptions().deny()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(GET, "/api/albuns/**").hasAuthority("SCOPE_albuns:read")
                .antMatchers(GET, "/api/albuns").hasAuthority("SCOPE_albuns:read")
                .antMatchers(POST, "/api/albuns").hasAuthority("SCOPE_albuns:write")
                .antMatchers(POST, "/api/albuns/*/figurinhas").hasAuthority("SCOPE_albuns:write")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt(); // atencao: necessario pois estamos sobrescrevendo a conf do .properties
        ;
        // @formatter:on
    }

}
