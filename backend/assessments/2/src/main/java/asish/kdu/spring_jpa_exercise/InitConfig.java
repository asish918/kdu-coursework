package asish.kdu.spring_jpa_exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class InitConfig implements CommandLineRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(InitConfig.class);

    @Override
    public void run(String...args) throws Exception {
        LOG.info("Hello");
    }
}