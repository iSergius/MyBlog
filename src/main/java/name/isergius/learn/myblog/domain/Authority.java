package name.isergius.learn.myblog.domain;

import name.isergius.learn.myblog.dao.Model;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * Created by Kondratyev Sergey on 11.11.15.
 */
public class Authority extends Model implements GrantedAuthority {

    private String title;
    private Set<User> users;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return title;
    }
}
