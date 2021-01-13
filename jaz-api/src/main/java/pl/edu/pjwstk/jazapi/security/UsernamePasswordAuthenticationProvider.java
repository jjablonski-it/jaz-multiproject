package pl.edu.pjwstk.jazapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var username = authentication.getPrincipal().toString();
        var password = authentication.getCredentials().toString();

        var userDetails = userService.loadUserByUsername(username);
        var matches = userService.getPasswordEncoder().matches(password, userDetails.getPassword());

        if (matches) {
            return new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()
            );
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
