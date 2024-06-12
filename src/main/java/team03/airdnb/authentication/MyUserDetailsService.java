package team03.airdnb.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team03.airdnb.user.User;
import team03.airdnb.user.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("사용자가 존재하지 않습니다: " + username));

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
    }
}
