package com.example.config;

public interface SecurityContextAccessor {
    boolean isCurrentAuthenticationAnonymous();
    boolean isCurrentAuthenticationAdmin();

}
