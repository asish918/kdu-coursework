package asish.kdu.sring_jdbc_homework.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompleteDTO {
    @JsonProperty("tenant")
    private TenantDTO tenant;
    @JsonProperty("shiftType")
    private ShiftTypeDTO shiftType;
    @JsonProperty("shifts")
    private ShiftsDTO shifts;
    @JsonProperty("users")
    private UsersDTO users;
}
