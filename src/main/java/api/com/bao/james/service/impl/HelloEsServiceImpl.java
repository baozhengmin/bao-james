package api.com.bao.james.service.impl;

import api.com.bao.james.dao.HelloEsMapper;
import api.com.bao.james.entity.HelloEs;
import api.com.bao.james.service.IHelloEsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午4:12$
 */
@Service
public class HelloEsServiceImpl implements IHelloEsService {

  @Autowired
  private HelloEsMapper helloEsMapper;

  @Override
  public void insertData(HelloEs helloEs) {
    helloEsMapper.save(helloEs);
  }

  @Override
  public List<HelloEs> getList() {
    return helloEsMapper.getList();
  }
}
