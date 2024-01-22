package asish.kdu.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * We point the Component Scan to services package
 * where all our Component Beans are present.
 * We don't define any new beans here.
 */
@Configuration
@ComponentScan(basePackages = "asish.kdu.services")
public class AppConfig {

}
