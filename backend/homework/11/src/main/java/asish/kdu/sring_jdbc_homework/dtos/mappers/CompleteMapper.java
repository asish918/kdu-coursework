package asish.kdu.sring_jdbc_homework.dtos.mappers;

import asish.kdu.sring_jdbc_homework.dtos.*;

public class CompleteMapper {
    private CompleteMapper() {

    }

    public static UsersDTO toUsersDto(CompleteDTO completeDTO, String tenantId, String userId) {
        return new UsersDTO(
                userId,
                completeDTO.getUsers().getUsername(),
                completeDTO.getUsers().getLoggedin(),
                completeDTO.getUsers().getTimezone(),
                tenantId
        );
    }

    public static TenantDTO toTenantDto(CompleteDTO completeDTO) {
        return new TenantDTO(
                completeDTO.getTenant().getName(),
                completeDTO.getTenant().getCreatedBy(),
                completeDTO.getTenant().getUpdatedBy(),
                completeDTO.getTenant().getEmail()
        );
    }

    public static ShiftsDTO toShiftsDto(CompleteDTO completeDTO, String tenantId, String shiftTypeId) {
        return new ShiftsDTO(
                shiftTypeId,
                completeDTO.getShifts().getName(),
                completeDTO.getShifts().getDateStart(),
                completeDTO.getShifts().getDateEnd(),
                completeDTO.getShifts().getTimeStart(),
                completeDTO.getShifts().getTimeEnd(),
                completeDTO.getShifts().getTimezone(),
                tenantId
        );
    }

    public static ShiftTypeDTO toShiftTypeDto(CompleteDTO completeDTO, String tenantId) {
        return new ShiftTypeDTO(
                completeDTO.getShiftType().getUqName(),
                completeDTO.getShiftType().getDescription(),
                completeDTO.getShiftType().getActive(),
                completeDTO.getShiftType().getCreatedBy(),
                completeDTO.getShiftType().getUpdatedBy(),
                completeDTO.getShiftType().getTimezone(),
                tenantId
        );
    }
}
