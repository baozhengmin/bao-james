package api.com.bao.james.service;

import api.com.bao.james.entity.HelloEs;
import java.util.List;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午4:11$
 */
public interface IHelloEsService {

  void insertData (HelloEs helloEs);
  List<HelloEs> getList();

}
