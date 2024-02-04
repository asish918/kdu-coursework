package com.kdu.smarthome.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Inventory table that keeps track of the
 * devices in the app. A device can be present
 * in one room at a time
 */
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
