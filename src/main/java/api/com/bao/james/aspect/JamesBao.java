package api.com.bao.james.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * $DESCRIPTION$
 *
 * @author : JamesBao
 * @version :
 * @date : Created in 上午10:40$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface JamesBao {

  int type() default 0;
  String desc() default "xx";
}
