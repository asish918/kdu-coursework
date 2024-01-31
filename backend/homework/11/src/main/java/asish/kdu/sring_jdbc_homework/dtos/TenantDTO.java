package asish.kdu.sring_jdbc_homework.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenantDTO {
    private String name;
    private String createdBy;
    private String updatedBy;
    private String email;
}
