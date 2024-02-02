package asish.kdu.spring_jpa_exercise.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalPrice;

    @OneToOne
    private Address address;
}
