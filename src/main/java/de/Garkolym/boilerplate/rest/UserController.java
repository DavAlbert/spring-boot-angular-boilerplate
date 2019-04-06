package de.Garkolym.boilerplate.rest;

import de.Garkolym.boilerplate.model.AuthUserModel;
import de.Garkolym.boilerplate.repository.AuthUserRepository;
import de.Garkolym.boilerplate.responses.UserInformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private AuthUserRepository authUserRepository;

    @Autowired
    public UserController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ResponseEntity<UserInformationResponse> fetchDetails(Principal principal) {
        AuthUserModel authUserModel = this.authUserRepository.findByUsername(principal.getName()).orElseThrow(EntityNotFoundException::new);
        UserInformationResponse userInformationResponse = new UserInformationResponse(authUserModel.getUsername(), authUserModel.getFirstName()
        , authUserModel.getLastName(), authUserModel.getEmail());
        return ResponseEntity.ok(userInformationResponse);
    }

}
