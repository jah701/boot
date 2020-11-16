package com.boot.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String extended_id;
    private String name;
    private String password;
    @ManyToMany
    private Set<Role> roles;
}
