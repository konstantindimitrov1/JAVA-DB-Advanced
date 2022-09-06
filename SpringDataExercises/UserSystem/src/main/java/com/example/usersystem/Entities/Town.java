package com.example.usersystem.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Town extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    public Town() {
    }

    public Town(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
