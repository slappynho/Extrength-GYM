package extrengthsupplements.extrength.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.security.config.Customizer.withDefaults;



@EnableWebSecurity
@Configuration
public class WebAuthorization {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()


                .antMatchers("/rest", "h2-console").hasAuthority("ADMIN")
                .antMatchers("/web/indexC.html").hasAuthority("CLIENT")
                .antMatchers("/web/products.html").hasAuthority("CLIENT")
                .antMatchers("/web/shopping-cart.html").hasAuthority("CLIENT")
                .antMatchers("/web/subscription.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/web/shopping-cart.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/web/subscription.html").hasAuthority("CLIENT")
                .antMatchers("/web/login.html").permitAll()
                .antMatchers("/web/index.html").permitAll()
                .antMatchers("/web/products-no-logged.html").permitAll()
                .antMatchers("/web/food-recipes.html").permitAll()
                .antMatchers("/web/recetas-steps-blueberry.html").permitAll()
                .antMatchers("/web/recetas-steps-cookies").permitAll()
                .antMatchers("/web/recetas-steps-juicemelocoton.html").permitAll()
                .antMatchers("/web/recetas-steps-melocoton.html").permitAll()
                .antMatchers("/web/recetas-steps-oatmeals").permitAll()
                .antMatchers("/web/recetas-steps-zucchinni.html").permitAll()
                .antMatchers(HttpMethod.POST, "/web/login.html").permitAll();





//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )


        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");


        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());




        return http.build();
    }


    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

    }





}
