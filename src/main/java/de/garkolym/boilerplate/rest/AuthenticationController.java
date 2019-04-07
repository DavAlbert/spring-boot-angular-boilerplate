package de.garkolym.boilerplate.rest;

import de.garkolym.boilerplate.model.AuthUserModel;
import de.garkolym.boilerplate.requests.UserLoginRequest;
import de.garkolym.boilerplate.requests.UserRegisterRequest;
import de.garkolym.boilerplate.responses.TokenResponse;
import de.garkolym.boilerplate.security.TokenHelper;
import de.garkolym.boilerplate.services.MyUserDetailsService;
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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUserModel authUserModel = (AuthUserModel) authentication.getPrincipal();
        String jwtToken = tokenHelper.generateToken(authUserModel.getUsername());
        int expiresIn = tokenHelper.getExpiredIn();
        return ResponseEntity.ok(new TokenResponse(jwtToken, expiresIn));
    }
}
