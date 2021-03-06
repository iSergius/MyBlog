package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public class User extends Model {

    private String username;
    private String password;
    private boolean enabled;
    private Set<GrantedAuthority> authorities;
    private String fullName;
    private String about;
    private String email;

    public User() {
    }

    public User(String username, String password) {
        this(username,password,false,new HashSet<GrantedAuthority>());
    }

    public User(String username, String password, boolean enabled) {
        this(username,password,enabled,new HashSet<GrantedAuthority>());
    }

    public User(String username, String password, boolean enabled, Set<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
