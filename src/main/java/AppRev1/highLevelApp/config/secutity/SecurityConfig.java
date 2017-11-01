package AppRev1.highLevelApp.config.secutity;

import AppRev1.highLevelApp.persistence.entity.Person;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    StrongTextEncryptor getEncryptor(){
        return new StrongTextEncryptor();
    }

    @Bean
    TokenProvider getTokenProvider(){
        return new TokenProvider();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return false;
            }
        })
                .userDetailsService(this.userDetailsService).passwordEncoder(this.getPasswordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        //Creating token when basic authentication is successful and the same token can be used to authenticate for further requests
        final CustomBasicAuthenticationFilter customBasicAuthFilter =
                new CustomBasicAuthenticationFilter(this.authenticationManager());
        http.addFilter(customBasicAuthFilter);

        //Implementing Token based authentication in this filter
        final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter();
        http.addFilterBefore(tokenFilter, CustomBasicAuthenticationFilter.class);

        http.csrf()
                .disable()
                // указываем правила запросов
                // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .and();

        http.formLogin()
                .permitAll()
                .loginPage("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .failureUrl("/login?error")
//                .successForwardUrl("/rest/test")
//                .defaultSuccessUrl("/main/Hello")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
                ;

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);

        http
//                .addFilterAfter(requestHeaderAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/rest/**").authenticated();

        http.exceptionHandling().authenticationEntryPoint(new AuthEntryPoint());

    }



//    @Bean
//    AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> testUserDetailsWrapper(){
////        return new DevUserDetailsWrapper();
//        return new UserDetailsWrapper();
//    }
//
//    @Autowired
//    AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authUserDetailsWrapper;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
//    }

//    @Bean
//    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
//        RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter = new RequestHeaderAuthenticationFilter();
//        requestHeaderAuthenticationFilter.setPrincipalRequestHeader("X-AUTH-TOKEN");
//        requestHeaderAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        requestHeaderAuthenticationFilter.setExceptionIfHeaderMissing(false);
////        requestHeaderAuthenticationFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
////            @Override
////            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////
////            }
////        })
//        return requestHeaderAuthenticationFilter;
//    }
//
//    @Bean
//    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
//        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
//        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(authUserDetailsWrapper);
//        return preAuthenticatedAuthenticationProvider;
//    }
}
