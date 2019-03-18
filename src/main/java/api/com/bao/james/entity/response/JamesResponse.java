package api.com.bao.james.entity.response;

import api.com.bao.james.Exception.JamesBaoException;
import api.com.bao.james.constant.ErrorCodeEnum;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午3:47$
 */
@Data
public class JamesResponse<T> implements Serializable {

  private Boolean success;
  private Integer errorCode;
  private String errorMsg;
  private T result;

  public static JamesResponse success() {

    JamesResponse jamesResponse = new JamesResponse();
    jamesResponse.setSuccess(Boolean.TRUE);

    return jamesResponse;
  }

  public static <T> JamesResponse<T> success(T result) {

    JamesResponse<T> jamesResponse = new JamesResponse<>();
    jamesResponse.setSuccess(Boolean.TRUE);
    jamesResponse.setResult(result);

    return jamesResponse;
  }

  public static <T> JamesResponse<T> error(ErrorCodeEnum errorCodeEnum) {

    JamesResponse<T> jamesResponse = new JamesResponse<>();
    jamesResponse.setSuccess(Boolean.FALSE);
    jamesResponse.setErrorCode(errorCodeEnum.code());
    jamesResponse.setErrorMsg(errorCodeEnum.message());

    return jamesResponse;
  }

  public static <T> JamesResponse<T> error(ErrorCodeEnum errorCodeEnum, String errorMsg) {

    JamesResponse<T> jamesResponse = new JamesResponse<>();
    jamesResponse.setSuccess(Boolean.FALSE);
    jamesResponse.setErrorCode(errorCodeEnum.code());
    jamesResponse.setErrorMsg(errorMsg);

    return jamesResponse;
  }

  public static <T> JamesResponse<T> error(JamesBaoException e) {

    JamesResponse<T> jamesResponse = new JamesResponse<>();
    jamesResponse.setSuccess(Boolean.FALSE);
    if (e != null) {
      jamesResponse.setErrorCode(e.getCode());
      jamesResponse.setErrorMsg(e.getMessage());
    }
    return jamesResponse;
  }

  public static <T> JamesResponse<T> error(JamesBaoException e, String errorMsg) {

    JamesResponse<T> jamesResponse = new JamesResponse<>();
    jamesResponse.setSuccess(Boolean.FALSE);
    if (e != null){
      jamesResponse.setErrorCode(e.getCode());
    }
    if (StringUtils.isNotBlank(errorMsg)) {
      jamesResponse.setErrorMsg(errorMsg);
    }
    return jamesResponse;
  }

  public static <T> JamesResponse<T> valueOf(Boolean result) {

    JamesResponse<T> jamesResponse = new JamesResponse<>();
    jamesResponse.setSuccess(result);
    jamesResponse.setResult(null);
    return jamesResponse;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
}
