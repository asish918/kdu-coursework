package com.kdu.smartHome.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "inventory")
public class Device {
    @Id
    private Long kickstonId;

    private String deviceUsername;

    private String devicePassword;

    private Timestamp manufactureDateTime;

    private String manufactureFactoryPlace;

    private Boolean registered = false;

    @OneToOne
    private Room room;
}
