package api.resource;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 上午11:16$
 */
public class ServletInitializer extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(JamesBaoConfigration.class);
  }


}
