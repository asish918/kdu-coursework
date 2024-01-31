package asish.kdu.sring_jdbc_homework.repository;

import asish.kdu.sring_jdbc_homework.models.ShiftType;
import asish.kdu.sring_jdbc_homework.models.Shifts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShiftRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("java:S2077")
    public void createShiftType(ShiftType shiftType) {
        String sql = "INSERT INTO shift_types (uq_name, description, active, created_by, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, UUID_TO_BIN(?))";
        jdbcTemplate.update(sql, shiftType.getUqName(), shiftType.getDescription(), shiftType.getActive(), shiftType.getCreatedBy(), shiftType.getUpdatedBy(), shiftType.getTimezone(), shiftType.getTenantId());
    }

    @SuppressWarnings("java:S2077")
    public void createShiftType(String id, ShiftType shiftType) {
        shiftType.setId(id);
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_by, updated_by, time_zone, tenant_id) VALUES (UUID_TO_BIN(?), ?, ?, ?, ?, ?, ?, UUID_TO_BIN(?))";
        jdbcTemplate.update(sql, shiftType.getId(), shiftType.getUqName(), shiftType.getDescription(), shiftType.getActive(), shiftType.getCreatedBy(), shiftType.getUpdatedBy(), shiftType.getTimezone(), shiftType.getTenantId());
    }

    @SuppressWarnings("java:S2077")
    public void createShifts(String id, Shifts shifts) {
        shifts.setId(id);
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, time_start, time_end, time_zone, tenant_id)\n" +
                "VALUES (UUID_TO_BIN(?), UUID_TO_BIN(?), ?, ?, ?, ?, ?, ?, UUID_TO_BIN(?));";
        jdbcTemplate.update(sql, shifts.getId(), shifts.getShiftTypeId(), shifts.getName(), shifts.getDateStart(), shifts.getDateEnd(), shifts.getTimeStart(), shifts.getTimeEnd(), shifts.getTimezone(), shifts.getTenantId());
    }

    @SuppressWarnings("java:S2077")
    public void createShifts(Shifts shifts) {
        String sql = "INSERT INTO shifts (shift_type_id, name, date_start, date_end, time_start, time_end, time_zone, tenant_id)\n" +
                "VALUES (UUID_TO_BIN(?), ?, ?, ?, ?, ?, ?, UUID_TO_BIN(?));";
        jdbcTemplate.update(sql, shifts.getShiftTypeId(), shifts.getName(), shifts.getDateStart(), shifts.getDateEnd(), shifts.getTimeStart(), shifts.getTimeEnd(), shifts.getTimezone(), shifts.getTenantId());
    }

    @SuppressWarnings("java:S2077")
    public List<ShiftType> getShiftsType(String tenantId) {
        String sql = "SELECT BIN_TO_UUID(id) AS ID, uq_name, description, active, created_at, updated_at, created_by, updated_by, time_zone, BIN_TO_UUID(tenant_id) AS tenant_id FROM shift_types WHERE tenant_id=UUID_TO_BIN('" + tenantId + "');";
        return new ArrayList<>(jdbcTemplate.query(sql,
                (rs, rowNum) -> new ShiftType(
                        rs.getString("ID"),
                        rs.getString("uq_name"),
                        rs.getString("description"),
                        rs.getBoolean("active"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("created_by"),
                        rs.getString("updated_by"),
                        rs.getString("time_zone"),
                        rs.getString("tenant_id"))));
    }

    @SuppressWarnings("java:S2077")
    public List<Shifts> getShifts(String tenantId) {
        String sql = "SELECT BIN_TO_UUID(id) AS id, BIN_TO_UUID(shift_type_id) AS shift_type_id, name, date_start, date_end, time_start, time_end, created_at, updated_at, time_zone, BIN_TO_UUID(tenant_id) AS tenant_id FROM shifts WHERE tenant_id=UUID_TO_BIN('" + tenantId + "');";
        return new ArrayList<>(jdbcTemplate.query(sql,
                (rs, rowNum) -> new Shifts(
                        rs.getString("id"),
                        rs.getString("shift_type_id"),
                        rs.getString("name"),
                        rs.getDate("date_start"),
                        rs.getDate("date_end"),
                        rs.getTime("time_start"),
                        rs.getTime("time_end"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getString("time_zone"),
                        rs.getString("tenant_id"))));
    }
}
