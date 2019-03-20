package api.com.bao.james.aspect;

import api.com.bao.james.Exception.JamesBaoException;
import api.com.bao.james.constant.ErrorCodeEnum;
import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

/**
 * @author: James Bao
 * @date: Created in 下午5:23 2017/11/15
 * @version: 1.0
 */
@Aspect
@Component
public class ParamValidAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParamValidAspect.class);

    @Pointcut("@annotation(api.com.bao.james.aspect.ValidParam)")
    public void controllerBefore(){

    }

    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Before("controllerBefore()")
    public void before(JoinPoint point) throws NoSuchMethodException, SecurityException, JamesBaoException {
        //  获得切入目标对象
        Object target = point.getThis();
        // 获得切入方法参数
        Object [] args = point.getArgs();
        // 获得切入的方法
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, args);

        // 解析形式参数名称
        if (CollectionUtils.isNotEmpty(validResult)) {
            List<String> res = validResult.stream().map((item)->{
                PathImpl path = (PathImpl)item.getPropertyPath();
                int index = path.getLeafNode().getParameterIndex();
                String paramName = parameterNameDiscoverer.getParameterNames(method)[index];
                return paramName + ":" + item.getMessage();
            }).collect(Collectors.toList());

            LOGGER.warn("the result is: {}", JSON.toJSONString(res));

            throw new JamesBaoException(ErrorCodeEnum.PARAM_INVALID, StringUtils.join(res, ","));
        }

    }

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();
    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object [] params){
        return validator.validateParameters(obj, method, params);
    }

}
