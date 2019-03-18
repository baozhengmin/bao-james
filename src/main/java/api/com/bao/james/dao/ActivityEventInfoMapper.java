package api.com.bao.james.dao;

import api.com.bao.james.entity.ActivityEventInfoDo;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * $DESCRIPTION$
 *
 * @author : ZhengMin Bao
 * @version :
 * @date : Created in 下午2:28$
 */

//@Repository
//@Component("activityEventInfoMapper")
public interface ActivityEventInfoMapper {

  void insertData(ActivityEventInfoDo activityEventInfoDo);

  List<ActivityEventInfoDo> getRecentlyEvent();
}
