package com.ecommerce.security;

import com.ecommerce.model.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private static final String[] PUBLIC_MATCHERS = {"/", "/bonjour", "/products", "/products/", "/client/registration", "/client/add"};

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = null;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                 .usernameParameter("email")
//                 .defaultSuccessUrl("/tasks")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


    @Target({TYPE,ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Constraint(validatedBy = PasswordMatchesValidator.class)
    @Documented
    public @interface PasswordMatches {
        String message() default "Error Passwords";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public class PasswordMatchesValidator
            implements ConstraintValidator<PasswordMatches, Object> {

        @Override
        public void initialize(PasswordMatches constraintAnnotation) {
        }
        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext context){
            ClientDto client = (ClientDto) obj;
            String password = client.getPassword();
            System.out.println(password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[%*$\\-+=])[A-Za-z\\d%*$\\-+=]{4,}$"));
            return password.equals(client.getMatchingPassword())
                    && password.length() >= 4
                    && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[%*$\\-+=])[A-Za-z\\d%*$\\-+=]{4,}$");
        }
    }

    @Target({TYPE,ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Constraint(validatedBy = UserIsValid.class)
    @Documented
    public @interface ValidUser {
        String message() default "Username first letter must be uppercase";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public class UserIsValid implements ConstraintValidator<ValidUser, Object> {

        @Override
        public void initialize(ValidUser constraintAnnotation) {}

        @Override
        public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
            ClientDto client = (ClientDto) obj;

            return client.getUsername().matches("[A-Z]\\S+");
        }
    }
}
