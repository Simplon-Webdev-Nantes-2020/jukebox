package co.simplon.jukebox.login;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Role name;

    public Authority() {}

    public Authority(Role name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getName() {
        return name;
    }

    public void setName(Role name) {
        this.name = name;
    }
}
