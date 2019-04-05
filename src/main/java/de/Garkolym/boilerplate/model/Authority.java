package de.Garkolym.boilerplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Authority extends AbstractEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private UserRole name = UserRole.ROLE_USER;

    @Override
    public String getAuthority() {
        return name.name();
    }

    @JsonIgnore
    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
