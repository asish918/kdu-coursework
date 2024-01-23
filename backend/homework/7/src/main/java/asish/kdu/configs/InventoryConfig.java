package asish.kdu.configs;


import asish.kdu.entities.Inventory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Inventory Config file that creates a Bean with Prototype scope
 * so that it creates a new instance everytime the Bean is created.
 */
@Configuration
public class InventoryConfig {
    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Inventory inventory() {
        return new Inventory();
    }
}
