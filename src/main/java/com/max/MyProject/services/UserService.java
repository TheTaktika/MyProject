package com.max.MyProject.services;

import com.max.MyProject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        System.out.println("DEBUG: Попытка входа пользователя: " + username);

        com.max.MyProject.entities.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("DEBUG: Пользователь [" + username + "] не найден в БД!");
                    return new UsernameNotFoundException("Пользователь не найден");
                });

        System.out.println("DEBUG: В базе найден пароль: " + user.getPassword());

        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}