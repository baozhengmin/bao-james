package api.com.bao.james.Exception;

import api.com.bao.james.constant.ErrorCodeEnum;
import lombok.Getter;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午3:25$
 */
public class JamesBaoException extends RuntimeException {

  @Getter
  private int code;
  public JamesBaoException(int code,String message){
    super(message);
    this.code = code;
  }
  public JamesBaoException(ErrorCodeEnum status) {
    this(status.code(), status.message());
  }

  public JamesBaoException(ErrorCodeEnum status, String msg) {
    this(status.code(), msg);
  }

  public static JamesBaoException exception(ErrorCodeEnum status) {
    return new JamesBaoException(status);
  }

  public static JamesBaoException exception(ErrorCodeEnum status, String msg) {
    return new JamesBaoException(status, msg);
  }
}
