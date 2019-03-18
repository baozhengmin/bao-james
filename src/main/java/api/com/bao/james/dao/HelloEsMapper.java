package api.com.bao.james.dao;

import api.com.bao.james.entity.HelloEs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * $DESCRIPTION$
 *
 * @author : your name here
 * @version :
 * @date : Created in 下午4:08$
 */
public interface HelloEsMapper extends ElasticsearchRepository<HelloEs,Long> {
  int saveData (HelloEs helloEs);
  List<HelloEs> getList();

  HelloEs getById(@Param("id") String id);
}
