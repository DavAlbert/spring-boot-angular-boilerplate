package de.Garkolym.boilerplate.services;

import de.Garkolym.boilerplate.model.AuthUserModel;
import de.Garkolym.boilerplate.model.Authority;
import de.Garkolym.boilerplate.repository.AuthUserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public MyUserDetailsService(AuthUserRepository authUserRepository, AuthenticationManager authenticationManager) {
        this.authUserRepository = authUserRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.authUserRepository.findByUsername(s).orElseThrow(EntityNotFoundException::new);
    }

    public void createNormalUser(String username, String firstName, String lastName, String email, String password) {
        AuthUserModel authUserModel = new AuthUserModel();
        authUserModel.setUsername(username);
        authUserModel.setFirstName(firstName);
        authUserModel.setLastName(lastName);
        authUserModel.setEmail(email);
        authUserModel.setPassword(getPasswordEncoder().encode(password));
        authUserModel.setAuthorities(Collections.singletonList(new Authority()));
        authUserModel.setEnabled(true);
        this.authUserRepository.save(authUserModel);
    }
}
