package api.com.bao.james.service.impl;

import api.com.bao.james.dao.ActivityEventInfoMapper;
import api.com.bao.james.entity.ActivityEventInfoDo;
import api.com.bao.james.service.IActivityEventInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * $DESCRIPTION$
 *
 * @author : your name here
 * @version :
 * @date : Created in 下午5:01$
 */
@Service
public class ActivityEventInfoService implements IActivityEventInfoService {

  @Autowired
  private ActivityEventInfoMapper activityEventInfoMapper;

  @Override
  public String getRecentlyEvent() {
    List<ActivityEventInfoDo> events = activityEventInfoMapper.getRecentlyEvent();

    return events.toString();
  }

  @Override
  public void saveActivityEventInfo(ActivityEventInfoDo eventInfoDo) {

  }
}
