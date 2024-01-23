package asish.kdu.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * We point the Component Scan to services package
 * where all our Component Beans are present.
 * We don't define any new beans here.
 * We also Import the Inventory Config.
 */
@Configuration
@ComponentScan(basePackages = "asish.kdu.services")
@Import({InventoryConfig.class})
public class AppConfig {

}
