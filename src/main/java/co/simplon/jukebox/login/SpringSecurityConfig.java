package co.simplon.jukebox.login;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ConditionalOnProperty(prefix = "app", name = "security", havingValue = "true", matchIfMissing = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * chiffrage du mot de passe avec Bcrypt
     * @return encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *  filtre des requetes
     *  url /admin => role ADMIN
     *      /jukebox => USER
     *  toute requete demande authentification
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    //.disable() // suppression ctrl xsrf
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //xsrf dans cookie
                    .and()
                .authorizeRequests()
//                .anyRequest().permitAll() //pour test sans authentification
                .antMatchers("/admin/*").hasRole("ADMIN")  //toutes les url admin requiert un ADMIN
                .antMatchers("/jukebox/playlists/**").hasRole("USER") //toutes les url playlist demande un USER
//                .anyRequest().authenticated()  //toutes les requetes demande une identification
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                ;
    }

    /**
     * 3 users avec 3 roles precis
     * users stockes en memoire
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("toto")
            .password(passwordEncoder().encode("toto"))
            .roles("USER")
            .and()
            .withUser("manu")
                .password(passwordEncoder().encode("manu"))
                .roles("USER","MANAGER")
            .and()
            .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN","MANAGER","USER")
            ;
    }
}
