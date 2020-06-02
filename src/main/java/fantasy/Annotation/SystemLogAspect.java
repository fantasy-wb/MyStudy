package fantasy.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


@Aspect
@Component
public class SystemLogAspect {


    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);

    //Controller层切点
    @Pointcut("execution (* fantasy.Annotation..*.*(..))")
    public void controllerAspect() {
    }

//    /**
//     * 前置通知 用于拦截Controller层记录用户的操作
//     *
//     * @param joinPoint 切点
//     */
//    @Before("controllerAspect()")
//    public void doBefore(JoinPoint joinPoint) {
//        System.out.println("==========执行controller前置通知===============");
//        if(logger.isInfoEnabled()){
//            logger.info("before " + joinPoint);
//        }
//    }

    //配置controller环绕通知,使用在方法aspect()上注册的切入点
      @Around("controllerAspect()")
      public void around(JoinPoint joinPoint) throws ClassNotFoundException {
          System.out.println("==========开始执行controller环绕通知===============");
          long start = System.currentTimeMillis();

          MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
          Method method = methodSignature.getMethod();
          if(method.isAnnotationPresent(Log.class)) {
              try {
                  ((ProceedingJoinPoint) joinPoint).proceed();
                  long end = System.currentTimeMillis();
                  if(logger.isInfoEnabled()){
                      logger.info("around " + method.getName() + "\tUse time : " + (end - start) + " ms!");
                  }
                  TimeUnit.SECONDS.sleep(2);
                  System.out.println("==========结束执行controller环绕通知===============");
              } catch (Throwable e) {
                  long end = System.currentTimeMillis();
                  if(logger.isInfoEnabled()){
                      logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
                  }
              }
          }

      }
}