package com.kdu.smartHome.entity.maps;

import com.kdu.smartHome.entity.House;
import com.kdu.smartHome.entity.UserInfo;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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
