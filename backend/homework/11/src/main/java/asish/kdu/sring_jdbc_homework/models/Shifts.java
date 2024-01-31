package asish.kdu.sring_jdbc_homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shifts {
    private String id;
    private String shiftTypeId;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Time timeStart;
    private Time timeEnd;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String timezone;
    private String tenantId;
}
