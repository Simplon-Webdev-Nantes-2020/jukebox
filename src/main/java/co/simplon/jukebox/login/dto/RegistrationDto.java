package co.simplon.jukebox.login.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RegistrationDto {

    @NotEmpty
    protected String username;

    @NotEmpty
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
             message= "Password must contain at least eight characters, at least one letter, one number and one special character")
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
