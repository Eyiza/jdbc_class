package app.config;

import app.service.DemoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"app.service", "app.controller"})
/*
    @ComponentScan tells spring to scan for components and adds them to the context
    You can add multiple packages separated by comma - {"app.service", "app.controller"}
    This is what @Autowired simplifies and does for us in spring boot.
*/
public class ProjectConfig {
    @Bean
    public DemoService demoService2(){
        return new DemoService();
    }
}
