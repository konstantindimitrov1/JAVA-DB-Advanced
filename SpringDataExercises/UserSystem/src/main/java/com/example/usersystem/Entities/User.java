package com.example.usersystem.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate registeredOn;

    @Column(nullable = false)
    private LocalDate lastTimeLoggedIn;

    @Column
    private int age;

    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne
    private Town bornTown;

    @ManyToOne
    private Town currentLivingTown;

    public User() {
    }

    public User(String username, String password, String email, LocalDate registeredOn, LocalDate lastTimeLoggedIn, int age, boolean isDeleted, Town bornTown, Town currentLivingTown) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
        this.bornTown = bornTown;
        this.currentLivingTown = currentLivingTown;
    }
}
