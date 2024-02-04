package com.kdu.smarthome.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

/**
 * House entity that has all the details
 * of a particular house. A user can be in multiple
 * houses and a house can be for multiple users hence
 * Many-to-Many relationship. A house can also have
 * multiple rooms
 */
@Entity
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @ManyToMany
    @JoinTable(name = "house_user_map", joinColumns = @JoinColumn(name = "house_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserInfo> users = new ArrayList<>();

    @OneToMany
    private List<Room> rooms = new ArrayList<>();
}
