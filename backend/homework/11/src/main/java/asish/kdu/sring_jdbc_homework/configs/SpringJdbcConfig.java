package asish.kdu.sring_jdbc_homework.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {
    private Environment env;

    @Autowired
    public SpringJdbcConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("db_url"));
        dataSource.setUsername(env.getProperty("db_user"));
        dataSource.setPassword(env.getProperty("db_password"));

        return dataSource;
    }
}