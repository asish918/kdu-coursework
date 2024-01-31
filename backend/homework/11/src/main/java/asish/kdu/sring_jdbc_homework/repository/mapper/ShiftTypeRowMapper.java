package asish.kdu.sring_jdbc_homework.repository.mapper;

import asish.kdu.sring_jdbc_homework.models.ShiftType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftTypeRowMapper implements RowMapper<ShiftType> {
    @Override
    public ShiftType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
