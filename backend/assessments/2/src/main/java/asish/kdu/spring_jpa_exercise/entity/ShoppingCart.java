package asish.kdu.spring_jpa_exercise.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "cart")
    private UserInfo userInfo;

    @ManyToMany(mappedBy = "cart")
    private List<Product> productList = new ArrayList<>();
}
