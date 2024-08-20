package sesac.sesaccdemo.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sesac.sesaccdemo.auth.dto.APIUserDTO;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class APIUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        APIUserDTO dto = new APIUserDTO(
                "a",
                "b",
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        return dto;
    }
}