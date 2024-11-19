package com.example.back_prueba_tecnica_civa.business;

import com.example.back_prueba_tecnica_civa.entity.UserEntity;
import com.example.back_prueba_tecnica_civa.entity.RoleEntity;
import com.example.back_prueba_tecnica_civa.models.request.AuthCreateUserRequest;
import com.example.back_prueba_tecnica_civa.models.request.AuthLoginRequest;
import com.example.back_prueba_tecnica_civa.models.response.AuthResponse;
import com.example.back_prueba_tecnica_civa.repository.CustomerRepository;
import com.example.back_prueba_tecnica_civa.repository.RoleRepository;
import com.example.back_prueba_tecnica_civa.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsBusiness implements UserDetailsService {

    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> findCustomer = userRepository.findByEmail(email);

        if (findCustomer.isEmpty()) {
            throw new UsernameNotFoundException("El cliente con el correo " + email + " no existe.");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        findCustomer.get().getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role)));
        findCustomer.get().getRoles().stream().flatMap(role -> role.getAuthorities().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(findCustomer.get().getEmail(), findCustomer.get().getPassword(), authorityList);
    }

    public AuthResponse createUser(AuthCreateUserRequest createUserRequest) {

        String username = createUserRequest.getUsername();
        String password = createUserRequest.getPassword();

        RoleEntity userRole = this.roleRepository.findByRoleName("USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(userRole);

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(password))
                .roles(roles)
                .build();

        UserEntity userSaved = this.userRepository.save(userEntity);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName()))));
        userSaved.getRoles().stream().flatMap(role -> role.getAuthorities().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = new UsernamePasswordAuthenticationToken(userSaved, password, authorities);

        String accessToken = jwtUtils.createToken(auth);

        return AuthResponse.builder()
                .username(username)
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .message("User created successfully")
                .token(accessToken)
                .build();
    }


    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String email = authLoginRequest.getEmail();
        String password = authLoginRequest.getPassword();

        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        return AuthResponse.builder()
                .email(email)
                .message("Login successfully")
                .token(accessToken)
                .build();
    }

    public Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }

}
