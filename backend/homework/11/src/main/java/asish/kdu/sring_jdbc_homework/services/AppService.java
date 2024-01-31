package asish.kdu.sring_jdbc_homework.services;

import asish.kdu.sring_jdbc_homework.dtos.*;
import asish.kdu.sring_jdbc_homework.dtos.mappers.*;
import asish.kdu.sring_jdbc_homework.models.ShiftType;
import asish.kdu.sring_jdbc_homework.models.Shifts;
import asish.kdu.sring_jdbc_homework.models.Tenant;
import asish.kdu.sring_jdbc_homework.models.Users;
import asish.kdu.sring_jdbc_homework.repository.ShiftRepository;
import asish.kdu.sring_jdbc_homework.repository.TenantRepository;
import asish.kdu.sring_jdbc_homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppService {
    private ShiftRepository shiftRepository;
    private UserRepository userRepository;
    private TenantRepository tenantRepository;

    @Autowired
    public AppService(ShiftRepository shiftRepository, UserRepository userRepository, TenantRepository tenantRepository) {
        this.shiftRepository = shiftRepository;
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
    }

    public void addAllEntries(CompleteDTO completeDTO) {
        UUID tenantId = UUID.randomUUID();
        UUID shiftTypeId = UUID.randomUUID();
        UUID shiftId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        TenantDTO tenantDTO = CompleteMapper.toTenantDto(completeDTO);
        Tenant tenant = TenantMapper.entityToDto(tenantDTO);
        tenant.setId(tenantId.toString());
        tenantRepository.createTenant(tenant);

        ShiftTypeDTO shiftTypeDTO = CompleteMapper.toShiftTypeDto(completeDTO, tenantId.toString());
        ShiftType shiftType = ShiftTypeMapper.dtoToEntity(shiftTypeDTO);
        shiftType.setTenantId(tenantId.toString());
        shiftRepository.createShiftType(shiftTypeId.toString(), shiftType);

        ShiftsDTO shiftsDTO = CompleteMapper.toShiftsDto(completeDTO, tenantId.toString(), shiftTypeId.toString());
        Shifts shifts = ShiftMapper.dtoToEntity(shiftsDTO);
        shifts.setTenantId(tenantId.toString());
        shiftRepository.createShifts(shiftId.toString(), shifts);

        UsersDTO usersDTO = CompleteMapper.toUsersDto(completeDTO, tenantId.toString(), userId.toString());
        Users users = UserMapper.dtoToEntity(usersDTO);
        users.setTenantId(tenantId.toString());
        userRepository.createUser(userId.toString(), users);
    }
}
