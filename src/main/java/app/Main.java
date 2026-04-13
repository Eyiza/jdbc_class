package app;

import app.config.ProjectConfig;
import app.controller.DemoController;
import app.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("Context: " + Arrays.toString(beanDefinitionNames));

        DemoService demoService = context.getBean(DemoService.class); // Retrieve the bean using the type of the bean
        System.out.println("Primary service: " + demoService);

        DemoService demoService1 = context.getBean("demoService", DemoService.class); // Retrieve the bean using the name of the bean
        System.out.println("Demo service Object 1: " + demoService1);

        DemoService demoService2 = context.getBean("demoService2", DemoService.class); // Retrieve the bean using the name of the bean
        System.out.println("Demo service Object 2: " + demoService2);

        DemoController demoController = context.getBean(DemoController.class);
        System.out.println("Demo Controller Bean: " + demoController);

        Constructor<?>[] constructors = DemoController.class.getConstructors();
    }
}
