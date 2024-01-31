package asish.kdu.sring_jdbc_homework.dtos.mappers;

import asish.kdu.sring_jdbc_homework.dtos.ShiftTypeDTO;
import asish.kdu.sring_jdbc_homework.models.ShiftType;

public class ShiftTypeMapper {
    private ShiftTypeMapper() {

    }

    public static ShiftType dtoToEntity(ShiftTypeDTO shiftTypeDTO) {
        ShiftType shiftType = new ShiftType();

        shiftType.setUqName(shiftTypeDTO.getUqName());
        shiftType.setDescription(shiftTypeDTO.getDescription());
        shiftType.setActive(shiftTypeDTO.getActive());
        shiftType.setCreatedBy(shiftTypeDTO.getCreatedBy());
        shiftType.setUpdatedBy(shiftTypeDTO.getUpdatedBy());
        shiftType.setTimezone(shiftTypeDTO.getTimezone());
        shiftType.setTenantId(shiftTypeDTO.getTenantId());

        return shiftType;
    }

    public static ShiftTypeDTO entityToDto(ShiftType shiftType) {
        return new ShiftTypeDTO(shiftType.getUqName(), shiftType.getDescription(), shiftType.getActive(), shiftType.getCreatedBy(), shiftType.getUpdatedBy(), shiftType.getTimezone(), shiftType.getTenantId());
    }
}
