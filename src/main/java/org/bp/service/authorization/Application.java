package org.bp.service.authorization;

import org.bp.security.spring.model.Authority;
import org.bp.security.spring.model.Role;
import org.bp.security.spring.model.User;
import org.bp.security.spring.repository.AuthorityRepository;
import org.bp.security.spring.repository.RoleRepository;
import org.bp.security.spring.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
@ComponentScan({
        "org.bp.security.spring",
        "org.bp.service.authorization"
})
@EnableJpaRepositories("org.bp.security.spring")
@EntityScan("org.bp.security.spring")
public class Application {

    private static void setupTestUsers(final ApplicationContext applicationContext) {
        final UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        final RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
        final AuthorityRepository authorityRepository = applicationContext.getBean(AuthorityRepository.class);

        final List<Authority> userAuthorities = List.of(authorityRepository.save(new Authority("CREATE_COLLECTION")));
        final Role userRole = roleRepository.save(new Role("USER", userAuthorities));

        //final List<Authority> adminAuthorities = (List<Authority>) authorityRepository.save(Collections.<Authority>emptyList());
        final Role adminRole = roleRepository.save(new Role("ADMIN", Collections.<Authority>emptyList()));

        userRepository.save(new User("user", "{noop}password", true, true, true, true, Arrays.asList(userRole)));
        userRepository.save(new User("volume-service", "{noop}password", true, true, true, true, Arrays.asList(userRole)));
        userRepository.save(new User("admin", "{noop}password", true, true, true, true, Arrays.asList(userRole, adminRole)));

    }

    public static void main(final String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        setupTestUsers(applicationContext);

    }

}