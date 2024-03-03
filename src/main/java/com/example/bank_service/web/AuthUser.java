package com.example.bank_service.web;

import com.example.bank_service.model.User;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Getter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }

    public String id() {
        return user.getId();
    }

    public static AuthUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        return (auth.getPrincipal() instanceof AuthUser au) ? au : null;
    }

    public static AuthUser get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static User authUser() {
        return get().getUser();
    }

    public static String authId() {
        return get().id();
    }

    @Override
    public String toString() {
        return "AuthUser:" + id() + '[' + user.getUsername() + ']';
    }
}