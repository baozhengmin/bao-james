package api.com.bao.james.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午4:06$
 */
@Data
@Document(indexName = "crm_glp",type = "hello_es", shards = 1,replicas = 0, refreshInterval = "-1")
public class HelloEs {


  @Id
  private String id;
  @Field
  private String type;
  @Field
  private String descrip;
  @Field
  private double price;
}
