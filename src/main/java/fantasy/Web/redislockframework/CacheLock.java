package fantasy.Web.redislockframework;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {

    //redis 锁key的前缀
	String lockedPrefix() default "";

    //锁时间
    long timeOut() default 2000;

    //key在redis里存在的时间，1000S
    int expireTime() default 100000;
}
