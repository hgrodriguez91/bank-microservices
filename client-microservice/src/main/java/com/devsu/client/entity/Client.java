package com.devsu.client.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client extends Person {
    @Column(unique = true, nullable = false, updatable = false)
    private String clientId;
    private String password;
    private Boolean status;

    @PrePersist
    public void generateClientId() {
        if (this.clientId == null) {
            this.clientId = java.util.UUID.randomUUID().toString();
        }
    }
}
