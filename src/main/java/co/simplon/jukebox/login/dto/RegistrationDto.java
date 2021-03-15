package co.simplon.jukebox.login.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RegistrationDto {

    @NotEmpty
    protected String username;

    @NotEmpty
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$")
    protected String password;

    @NotEmpty
    @Pattern(regexp="^(\\w||\\.)+@\\w+\\.\\w+$")
    protected String email;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
