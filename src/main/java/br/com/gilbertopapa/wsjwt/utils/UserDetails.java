package br.com.gilbertopapa.wsjwt.utils;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.List;

public class UserDetails implements Principal {

    private final String username;
    private final List<String> roles;


    public UserDetails(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
