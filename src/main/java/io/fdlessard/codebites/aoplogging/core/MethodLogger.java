package io.fdlessard.codebites.aoplogging.core;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.slf4j.event.Level;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface MethodLogger {

    Level logLevel() default Level.DEBUG;

    boolean logParameters() default true;

    boolean logResult() default true;
}