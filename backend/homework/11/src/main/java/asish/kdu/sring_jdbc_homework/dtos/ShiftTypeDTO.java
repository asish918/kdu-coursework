package asish.kdu.sring_jdbc_homework.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftTypeDTO {
    private String uqName;
    private String description;
    private Boolean active;
    private String createdBy;
    private String updatedBy;
    private String timezone;
    private String tenantId;
}
