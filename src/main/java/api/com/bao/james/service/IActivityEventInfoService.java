package api.com.bao.james.service;


import api.com.bao.james.entity.ActivityEventInfoDo;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 上午11:14$
 */
public interface IActivityEventInfoService {


  String getRecentlyEvent();

  void saveActivityEventInfo(ActivityEventInfoDo eventInfoDo);
}
