package api.com.bao.james.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 上午11:38$
 */
@Data
public class ActivityEventInfoDTO {

  /**
   * 主键
   */
  private Long id;
  @NotNull(message = "日志来源不能为空")
  @ApiModelProperty(value = "日志来源")
  private Integer source;
  @NotNull(message = "时间等级不能为空")
  @ApiModelProperty(value = "时间等级")
  private Integer eventLevel;
  @NotNull(message = "可能原因不能为空")
  @ApiModelProperty(value = "可能原因")
  private Integer eventCode;
  @NotNull(message = "描述不能为空")
  @ApiModelProperty(value = "描述")
  private String eventDesc;
}
