package com.example.messagingstompwebsocket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class User {

    private String id;

    @Setter
    private String email;

    @Setter
    @JsonIgnore
    private String password;

    @Setter
    private boolean isAdmin;

    @Setter
    private boolean enabled = true;

    @Setter
    private String key;

    @Setter
    private long credits;

    public User(String email, boolean isAdmin, boolean enabled, String key) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.enabled = enabled;
        this.key = key;
    }

    public User(String email, String password, boolean isAdmin, boolean enabled, String key) {
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.enabled = enabled;
        this.key = key;
    }
}
