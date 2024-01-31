package asish.kdu.sring_jdbc_homework.dtos.mappers;

import asish.kdu.sring_jdbc_homework.dtos.ShiftsDTO;
import asish.kdu.sring_jdbc_homework.models.ShiftType;
import asish.kdu.sring_jdbc_homework.models.Shifts;

import java.sql.Date;
import java.sql.Time;

public class ShiftMapper {
    private ShiftMapper() {

    }

    public static Shifts dtoToEntity(ShiftsDTO shiftsDTO) {
        Shifts shifts = new Shifts();

        shifts.setShiftTypeId(shiftsDTO.getShiftTypeId());
        shifts.setName(shiftsDTO.getName());
        shifts.setDateStart(Date.valueOf(shiftsDTO.getDateStart()));
        shifts.setDateEnd(Date.valueOf(shiftsDTO.getDateEnd()));
        shifts.setTimeStart(Time.valueOf(shiftsDTO.getTimeStart()));
        shifts.setTimeEnd(Time.valueOf(shiftsDTO.getTimeEnd()));
        shifts.setTimezone(shiftsDTO.getTimezone());
        shifts.setTenantId(shiftsDTO.getTenantId());

        return shifts;
    }

    public static ShiftsDTO entityToDto(Shifts shifts) {
        return new ShiftsDTO(
                shifts.getShiftTypeId(),
                shifts.getName(),
                shifts.getDateStart().toString(),
                shifts.getDateEnd().toString(),
                shifts.getTimeStart().toString(),
                shifts.getTimeEnd().toString(),
                shifts.getTimezone(),
                shifts.getTenantId()
        );
    }
}
