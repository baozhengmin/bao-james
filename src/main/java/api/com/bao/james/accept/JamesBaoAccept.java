package api.com.bao.james.accept;

import api.com.bao.james.Exception.JamesBaoException;
import api.com.bao.james.constant.ErrorCodeEnum;
import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * $DESCRIPTION$
 *
 * @author : James
 * @version :
 * @date : Created in 上午10:52$
 */
@Slf4j
@Aspect
@Component
@Order(-5)
public class JamesBaoAccept {

  private static final String UNKNOWN = "unknown";

  @Pointcut("@annotation(api.com.bao.james.accept.JamesBao)")
  public void controllerAspect(){

  }
  @Around("controllerAspect()")
  public void accept(ProceedingJoinPoint joinPoint){
    log.info("++++++++++++++++获取到拦截+++++++");
    log.info("----------------ip is"+ getIPAddress());
    JamesBao annon = this.getAnnotation(joinPoint);
    log.info("++++++++++++type = "+annon.type());
    log.info("++++++++++++desc = "+annon.desc());

  }

  private static final String API_SOURCE_KEY = "jamesBao";


  public String getIPAddress() {

    String ip = null;
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String apiKey = request.getHeader("apiKey");
    log.info("++++++++http request header "+apiKey);
    if(!API_SOURCE_KEY.equals(apiKey)){
      throw new JamesBaoException(ErrorCodeEnum.UNAUTHORIZED);
    }
    //X-Forwarded-For：Squid 服务代理
    String ipAddresses = request.getHeader("X-Forwarded-For");

    if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
      //Proxy-Client-IP：apache 服务代理
      ipAddresses = request.getHeader("Proxy-Client-IP");
    }

    if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
      //WL-Proxy-Client-IP：weblogic 服务代理
      ipAddresses = request.getHeader("WL-Proxy-Client-IP");
    }

    if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
      //HTTP_CLIENT_IP：有些代理服务器
      ipAddresses = request.getHeader("HTTP_CLIENT_IP");
    }

    if (ipAddresses == null || ipAddresses.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
      //X-Real-IP：nginx服务代理
      ipAddresses = request.getHeader("X-Real-IP");
    }

    //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
    if (ipAddresses != null && ipAddresses.length() != 0) {
      ip = ipAddresses.split(",")[0];
    }

    //最后再通过request.getRemoteAddr();获取
    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddresses)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  /**
   * 获取注解属性
   *
   * @param joinPoint
   * @return
   * @throws Exception
   */
  public JamesBao getAnnotation(ProceedingJoinPoint joinPoint) {

    //获取连接点目标类名
    String className = joinPoint.getTarget().getClass().getName();
    //获取连接点签名的方法名
    String methodName = joinPoint.getSignature().getName();
    //获取连接点参数
    Object[] args = joinPoint.getArgs();
    //根据连接点类的名字获取指定类
    Class targetClass;
    try {
      targetClass = Class.forName(className);
    } catch (Exception e) {
      log.info(JSON.toJSONString(e));
      return null;
    }

    //拿到类里面的方法
    Method[] methods = targetClass.getMethods();
    //遍历方法名，找到被调用的方法名
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        Class[] clazzs = method.getParameterTypes();
        if (clazzs.length == args.length) {
          //获取注解的说明
          JamesBao annotation = method.getAnnotation(JamesBao.class);
          return annotation;
        }
      }
    }

    return null;
  }


}
