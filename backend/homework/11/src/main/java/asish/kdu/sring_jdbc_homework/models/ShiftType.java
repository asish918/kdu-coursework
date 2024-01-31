package asish.kdu.sring_jdbc_homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftType {
    private String id;
    private String uqName;
    private String description;
    private Boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
    private String updatedBy;
    private String timezone;
    private String tenantId;
}
