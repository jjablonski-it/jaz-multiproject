package pl.edu.pjwstk.jazapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final List<User> users;
    private final PasswordEncoder passwordEncoder;

    public UserService() {
        this.users = new ArrayList<>();
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        GrantedAuthority adminAuthority = () -> "ROLE_ADMIN";
        List<GrantedAuthority> authorities = List.of(adminAuthority);
        var encodedPassword = passwordEncoder.encode("admin");
        users.add(new User("admin", encodedPassword, authorities));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .orElse(null);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
