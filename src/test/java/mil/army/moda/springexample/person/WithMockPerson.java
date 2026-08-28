package mil.army.moda.springexample.person;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockPersonSecurityContextFactory.class)
public @interface WithMockPerson {
    String role() default "TEACHER";
    String[] assignments() default {};
}