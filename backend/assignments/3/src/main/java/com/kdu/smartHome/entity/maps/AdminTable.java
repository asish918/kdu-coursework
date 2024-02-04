package com.kdu.smartHome.entity.maps;

import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.UserInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is the admin table
 * that maps a House with an Admin.
 * It helps to keep track of who is the admin
 * user of a particular house
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "admin_table")
public class AdminTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    House house;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserInfo user;
}
