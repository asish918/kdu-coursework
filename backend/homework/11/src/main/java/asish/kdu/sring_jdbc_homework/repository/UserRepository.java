package asish.kdu.sring_jdbc_homework.repository;

import asish.kdu.sring_jdbc_homework.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("java:S2077")
    public void createUser(Users users) {
        String sql = "INSERT INTO users (username, loggedin, time_zone, tenant_id)\n" +
                "VALUES  (?, ?, ?, UUID_TO_BIN(?));";
        jdbcTemplate.update(sql, users.getUsername(), users.getLoggedin(), users.getTimezone(), users.getTenantId());
    }

    @SuppressWarnings("java:S2077")
    public void createUser(String id, Users users) {
        users.setId(id);
        String sql = "INSERT INTO users (id, username, loggedin, time_zone, tenant_id)\n" +
                "VALUES  (UUID_TO_BIN(?), ?, ?, ?, UUID_TO_BIN(?));";
        jdbcTemplate.update(sql, users.getId(), users.getUsername(), users.getLoggedin(), users.getTimezone(), users.getTenantId());
    }

    @SuppressWarnings("java:S2077")
    public void updateUser(Users users) {
        String sql = "UPDATE users SET username = ?, loggedin = ? WHERE id = UUID_TO_BIN(?);";
        jdbcTemplate.update(sql, users.getUsername(), users.getLoggedin(), users.getId());
    }

    @SuppressWarnings("java:S2077")
    public List<Users> getUsers(String tenantId) {
        String sql = "SELECT BIN_TO_UUID(id) AS id, username, loggedin, time_zone, BIN_TO_UUID(tenant_id) AS tenant_id FROM users WHERE tenant_id = UUID_TO_BIN('" + tenantId + "');";
        return new ArrayList<>(jdbcTemplate.query(sql,
                (rs, rowNum) -> new Users(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getInt("loggedin"),
                        rs.getString("time_zone"),
                        rs.getString("tenant_id"))));
    }
}
