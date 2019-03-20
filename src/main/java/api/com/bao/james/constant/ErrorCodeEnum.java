package api.com.bao.james.constant;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午3:27$
 */
public enum ErrorCodeEnum {
  // 系统异常
  SUCCESS(200, "请求处理成功"),
  UNAUTHORIZED(401, "校验数据权限失败"),
  SERVICE_ERROR(500, "系统异常"),

  PARAM_NOT_NULL(501,"参数校验失败"),
  PARAM_INVALID(5002, "请求参数无效")
  ;


  private Integer code;

  private String message;
  ErrorCodeEnum(Integer status, String message) {
    this.code = status;
    this.message = message;
  }

  public ErrorCodeEnum getErrorCodeEnum(Integer status) {

    if (status == null) {
      return null;
    }

    for (ErrorCodeEnum errorCodeEnum : values()) {
      if (errorCodeEnum.code().equals(status)) {
        return errorCodeEnum;
      }
    }
    return null;
  }

  public Integer code() {
    return this.code;
  }

  public String message() {
    return this.message;
  }
}
