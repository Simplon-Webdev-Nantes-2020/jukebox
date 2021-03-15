package co.simplon.jukebox.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ConditionalOnProperty(prefix = "app", name = "security", havingValue = "true", matchIfMissing = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.server.client}")
    private String urlClientServer;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

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
    /*
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

    }*/
    protected void configure(HttpSecurity http) throws Exception {

        // Enable CORS
        http.cors();

        // Disable CSRF (cross site request forgery as our token will be stored in session storage)
        http.csrf().disable();

        // Add handler to answer 401 when not authenticated
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()//
                // identifcation
                .antMatchers("/signin").permitAll()//
                .antMatchers("/signup").permitAll()//
                .and()

                //h2-console frame
                .headers().frameOptions().sameOrigin()
                .and()

                //permission sur les urls
                .authorizeRequests() //requetes autorisees
                .antMatchers("/h2-console/**").permitAll()  //autoriser h2-console
                .antMatchers("/admin/*").hasRole("ADMIN")  //toutes les url admin requiert un ADMIN
                .antMatchers("/jukebox/playlists/**").hasRole("USER") //toutes les url playlist demande un USER

                .anyRequest().permitAll()
                // Disallow everything else...
                //.anyRequest().authenticated()
        ;

        // Apply JWT
        http.addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Generic configuration for CORS. Useful here for development purposes as front is developed with Angular.
     *
     * @return the CorsConfigurationSource object.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList(urlClientServer));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}
