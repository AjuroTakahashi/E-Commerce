package com.ecommerce.model;

import com.ecommerce.security.WebSecurityConfig.ValidUser;
import com.ecommerce.security.WebSecurityConfig.PasswordMatches;
import com.sun.istack.NotNull;

@PasswordMatches
@ValidUser
public class ClientDto {
    @NotNull
    private String username;

    @NotNull
    private String password;
    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
