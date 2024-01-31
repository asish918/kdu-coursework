package asish.kdu.sring_jdbc_homework.dtos.mappers;

import asish.kdu.sring_jdbc_homework.dtos.TenantDTO;
import asish.kdu.sring_jdbc_homework.models.Tenant;

public class TenantMapper {
    private TenantMapper() {

    }

    public static Tenant entityToDto(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();

        tenant.setName(tenantDTO.getName());
        tenant.setCreatedBy(tenantDTO.getCreatedBy());
        tenant.setUpdatedBy(tenantDTO.getUpdatedBy());
        tenant.setEmail(tenantDTO.getEmail());

        return tenant;
    }
}
