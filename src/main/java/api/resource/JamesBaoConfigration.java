package api.resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午3:21$
 */

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@ComponentScan(basePackages = {"mapping.*"})
@EnableSwagger2
//@MapperScan("mapping")
@ComponentScan(basePackages = {"api.*"})
@MapperScan("api.com.bao.james.dao")
public class JamesBaoConfigration {

  public static void main(String[] args){
    SpringApplication.run(JamesBaoConfigration.class,args);
  }
}
