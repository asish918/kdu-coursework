package asish.kdu.sring_jdbc_homework.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersDTO {
    private String id;
    private String username;
    private Short loggedin;
    private String timezone;
    private String tenantId;
}
