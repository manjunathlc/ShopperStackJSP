package edu.jsp.ShoperStack.configuration.secutriy;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private final ApplicationUserDetailsService userDetailsService;

    private static final String[] PUBLIC_URL = {"/api/version/users/save", "/swagger-ui/index.html/"};
    private static final String[] ADMIN_URL = {"/api/version/cart/getAllCarts"};
    private static final String[] MERCHANT_URL = {"/api/version/product/save",
            "/api/version/product/update",
            "/api/version/product/modifyProduct",
            "/api/version/product/updatePrice"};

    private static final String[] CUSTOMER_URL={"/api/version/cart/saveCart" ,
            "/api/version/cart/addProduct" ,
            "/api/version/cart/getCartById/{cartId}" ,
            "/api/version/cart/deleteCart/{cartId}"};

    private static final String[] MERCHANT_CUSTOMER_URL={"/api/version/product/getProductById/productId" ,
            "/api/version/product/getAllProducts"};


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers(PUBLIC_URL).permitAll();
            auth.requestMatchers(ADMIN_URL).hasRole("ADMIN");
            auth.requestMatchers(MERCHANT_URL).hasRole("MERCHANT");
            auth.requestMatchers(CUSTOMER_URL).hasRole("CUSTOMER");
            auth.requestMatchers(MERCHANT_CUSTOMER_URL).hasAnyRole("MERCHANT", "CUSTOMER");

            auth.anyRequest().authenticated();
        });

        httpSecurity.httpBasic(withDefaults());

        //Enable Cross-Site Request Forgery (CSRF) protection
        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

