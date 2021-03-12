package co.simplon.jukebox.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ConditionalOnProperty(prefix = "app", name = "security", havingValue = "true", matchIfMissing = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

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
                .csrf()//config csrf
                    //.disable() // suppression ctrl xsrf
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //xsrf dans cookie
                    .ignoringAntMatchers("/h2-console/**") //pas de xsrf pour h2-console
                .and()
                .headers().frameOptions().sameOrigin()//h2-console frame
                .and()
                .authorizeRequests() //requetes autorisees
//                  .anyRequest().permitAll() //pour test sans authentification
                    .antMatchers("/h2-console/**").permitAll()  //autoriser h2-console
                    .antMatchers("/admin/*").hasRole("ADMIN")  //toutes les url admin requiert un ADMIN
                    .antMatchers("/jukebox/playlists/**").hasRole("USER") //toutes les url playlist demande un USER
//                .anyRequest().authenticated()  //toutes les requetes demande une identification
                .and()
                .formLogin().permitAll() //login pour tous
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                ;

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
