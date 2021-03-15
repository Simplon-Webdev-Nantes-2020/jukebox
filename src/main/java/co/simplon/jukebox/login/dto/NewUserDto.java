package co.simplon.jukebox.login.dto;

import co.simplon.jukebox.login.model.Authority;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

public class NewUserDto extends RegistrationDto{

    @ManyToMany
    private Set<Authority> authorities = new HashSet<>();

    public NewUserDto(@NotEmpty String username, String password, @NotEmpty @Pattern(regexp = "^(\\w||\\.)+@\\w+\\.\\w+$") String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

}
