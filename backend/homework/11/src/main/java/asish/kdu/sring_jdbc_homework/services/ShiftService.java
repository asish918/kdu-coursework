package asish.kdu.sring_jdbc_homework.services;

import asish.kdu.sring_jdbc_homework.dtos.ShiftTypeDTO;
import asish.kdu.sring_jdbc_homework.dtos.ShiftsDTO;
import asish.kdu.sring_jdbc_homework.dtos.mappers.ShiftMapper;
import asish.kdu.sring_jdbc_homework.dtos.mappers.ShiftTypeMapper;
import asish.kdu.sring_jdbc_homework.models.ShiftType;
import asish.kdu.sring_jdbc_homework.models.Shifts;
import asish.kdu.sring_jdbc_homework.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {
    private ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void addShiftType(ShiftTypeDTO shiftTypeDTO) {
        ShiftType shiftType = ShiftTypeMapper.dtoToEntity(shiftTypeDTO);
        shiftRepository.createShiftType(shiftType);
    }

    public void addShifts(ShiftsDTO shiftsDTO) {
        Shifts shifts = ShiftMapper.dtoToEntity(shiftsDTO);
        shiftRepository.createShifts(shifts);
    }

    public List<ShiftType> getShiftType(String tenantId) {
        return shiftRepository.getShiftsType(tenantId);
    }

    public List<Shifts> getShifts(String tenantId) {
        return shiftRepository.getShifts(tenantId);
    }
}
