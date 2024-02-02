package asish.kdu.spring_jpa_exercise.dtos;

import lombok.Data;

@Data
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private Integer postalCode;
}
