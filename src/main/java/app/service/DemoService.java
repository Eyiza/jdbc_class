package app.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
/*
    Primary returns this object when there are other objects,
    and you want this return by default when the class type is asked for in the getBean method.
    It can also be used under the @Bean annotation to add items to the context.
 */
public class DemoService {
}
