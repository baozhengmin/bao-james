//package api.resource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
///**
// * $DESCRIPTION$
// *
// * @author : James Bao
// * @version :
// * @date : Created in 上午11:41$
// */
//
//@Configuration
//  public class SwaggerConfig {
//
//  @Bean
//  public Docket createRestApi() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .groupName("api.com.bao.james")
//        .apiInfo(apiInfo())
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("api.com.bao.james"))
//        .paths(PathSelectors.any())
//        .build();
//  }
//
//  private ApiInfo apiInfo() {
//    return new ApiInfoBuilder()
//        .title("Truckerpathapi swagger")
//        .description("James Bao jamesbao platform api，http://www.james-bao.com")
//        .termsOfServiceUrl("www.bao.james.com")
//        .version("1.0")
//        .build();
//  }
//}
//
