package com.alevel.service.impl;


import com.alevel.application.config.security.AdminUserDetails;
import com.alevel.dao.AdminDetailsDAO;
import com.alevel.model.Admin;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminDetailsServiceImpl implements UserDetailsService {

    AdminDetailsDAO adminDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminDetailsRepository.findByName(username);
        log.debug("Getting admin by name");
        return admin.map(AdminUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with username '%s' was not found", username)));
    }
}
