package com.pet.buyselltrade.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email" ,unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "userName")
    private String userName;
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade =  CascadeType.ALL,fetch = FetchType.EAGER)
    @Column(name = "image_id")
    private ImageModel avatar ;
    private String pass;
    private Set<Role> roles = new HashSet<>();
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
