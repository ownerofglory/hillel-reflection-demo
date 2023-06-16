package ua.ithillel.reflect.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Retention(RetentionPolicy.SOURCE) // src code, visible for compiler
//@Retention(RetentionPolicy.CLASS) // visible for compiler, but not for JVM
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyAnnotation {
    String value() default "";
    int count() default 5;
}
