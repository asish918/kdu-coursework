package asish.kdu.sring_jdbc_homework.repository;

import asish.kdu.sring_jdbc_homework.models.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TenantRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("java:S2077")
    public void createTenant(Tenant tenant) {
        String sql = "INSERT INTO tenant (id, name, created_by, updated_by, email) VALUES (UUID_TO_BIN(?), ?, ?, ?, ?);";
        jdbcTemplate.update(sql, tenant.getId(), tenant.getName(), tenant.getCreatedBy(), tenant.getUpdatedBy(), tenant.getEmail());
    }
}
