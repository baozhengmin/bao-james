package api.com.bao.james.entity;

import java.util.Date;
import lombok.Data;

/**
 * $DESCRIPTION$
 *
 * @author : ZhengMin Bao
 * @version :
 * @date : Created in 下午2:13$
 */
@Data
public class ActivityEventInfoDo {

  /**
   * 主键
   */
  private Long id;
  /**
   *日志来源
   */
  private Integer source;
  /**
   *时间等级
   */
  private Integer eventLevel;
  /**
   *可能原因
   */
  private Integer eventCode;
  /**
   *描述
   */
  private String eventDesc;
  private Date createTime;
  private Date updateTime;
  private int deleted;
}
