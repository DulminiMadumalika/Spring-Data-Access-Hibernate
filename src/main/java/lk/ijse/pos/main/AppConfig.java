package lk.ijse.pos.main;

import lk.ijse.pos.db.HibernateUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HibernateConfig.class})
@ComponentScan(basePackages = "lk.ijse.pos")
public class AppConfig {

}
