package com.codewebprojects.ecom.dto;
import lombok.Data;
@Data
public class AuthenticationRequest {
private String username;
private String password;
    public String getUsername() {
        return username;
    }

    // Setter pour username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter pour password
    public String getPassword() {
        return password;
    }

    // Setter pour password
    public void setPassword(String password) {
        this.password = password;
    }



}
