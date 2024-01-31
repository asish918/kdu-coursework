package asish.kdu.sring_jdbc_homework.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftsDTO {
    private String shiftTypeId;
    private String name;
    private String dateStart;
    private String dateEnd;
    private String timeStart;
    private String timeEnd;
    private String timezone;
    private String tenantId;
}
