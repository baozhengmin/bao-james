package api.com.bao.james.utils;

import api.com.bao.james.Exception.JamesBaoException;
import api.com.bao.james.constant.ErrorCodeEnum;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.HibernateValidator;

/**
 * @Author: jx
 * @Date: 2018/9/18 10:22
 */
public class ValidationUtils {


    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 注解验证参数
     *
     * @param obj
     */
    public static <T> void checkParams(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            throw new JamesBaoException(ErrorCodeEnum.PARAM_NOT_NULL, String.format("参数校验失败:%s",
                    constraintViolations.iterator().next().getMessage()));
        }
    }

    public static <T> void checkParams(List<T> list) {

        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        for (T t : list) {
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
            // 抛出检验异常
            if (constraintViolations.size() > 0) {
                throw new JamesBaoException(ErrorCodeEnum.PARAM_NOT_NULL, String.format("参数校验失败:%s",
                        constraintViolations.iterator().next().getMessage()));
            }
        }

    }
}
