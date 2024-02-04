package com.kdu.smartHome.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import com.kdu.smartHome.entity.maps.AdminTable;

import lombok.Data;

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
