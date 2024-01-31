package asish.kdu.sring_jdbc_homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String id;
    private String username;
    private Integer loggedin;
    private String timezone;
    private String tenantId;
}
