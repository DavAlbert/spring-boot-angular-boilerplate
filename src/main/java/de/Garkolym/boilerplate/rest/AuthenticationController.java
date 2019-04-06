package de.Garkolym.boilerplate.rest;

import de.Garkolym.boilerplate.model.AuthUserModel;
import de.Garkolym.boilerplate.requests.UserLoginRequest;
import de.Garkolym.boilerplate.requests.UserRegisterRequest;
import de.Garkolym.boilerplate.responses.TokenResponse;
import de.Garkolym.boilerplate.security.TokenHelper;
import de.Garkolym.boilerplate.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    private final TokenHelper tokenHelper;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public AuthenticationController(TokenHelper tokenHelper, AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService) {
        this.tokenHelper = tokenHelper;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity registerAccount(@RequestBody UserRegisterRequest userRegisterRequest) {
        this.myUserDetailsService.createNormalUser(
                userRegisterRequest.getUsername(),
                userRegisterRequest.getFirstName(),
                userRegisterRequest.getLastName(),
                userRegisterRequest.getEmail(),
                userRegisterRequest.getPassword()
        );
        String jwtToken = tokenHelper.generateToken(userRegisterRequest.getUsername());
        int expiresIn = tokenHelper.getExpiredIn();
        return ResponseEntity.ok(new TokenResponse(jwtToken, expiresIn));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginAccount(@RequestBody UserLoginRequest userLoginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUserModel authUserModel = (AuthUserModel) authentication.getPrincipal();
        String jwtToken = tokenHelper.generateToken(authUserModel.getUsername());
        int expiresIn = tokenHelper.getExpiredIn();
        return ResponseEntity.ok(new TokenResponse(jwtToken, expiresIn));
    }
}
