package api.com.bao.james.controller;

import api.com.bao.james.Exception.JamesBaoException;
import api.com.bao.james.accept.JamesBao;
import api.com.bao.james.dao.HelloEsMapper;
import api.com.bao.james.entity.HelloEs;
import api.com.bao.james.entity.response.JamesResponse;
import api.com.bao.james.service.IHelloEsService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 下午4:14$
 */
@RestController
@Api("HelloEsController!")
@RequestMapping("helloEs/")
@Slf4j
public class HelloEsController {

  @GetMapping("ping")
  @ApiOperation(notes = "ping",value = "this is heart beat test")
  @JamesBao(type = 2,desc = "heart beat")
  public JamesResponse heartBeat(){
    try{
      return JamesResponse.success("pong");
    }catch (JamesBaoException e){
      log.info("+++++++++++++++ {}",e);
      return JamesResponse.error(e);
    }
  }


  @Autowired
  private IHelloEsService helloEsService;

  @ApiOperation(notes = "test elasticSearch" ,value = " test elasticSearch")
  @GetMapping("getList")
  @JamesBao(type = 1,desc = "getList")
  public List<HelloEs> getList(){
    List<HelloEs> list = helloEsService.getList();
    return list;
  }
  @Autowired
  private HelloEsMapper helloEsMapper;
  @PostMapping("addModle")
  @JamesBao(type = 1,desc = "addModle")
  public String addModle(HelloEs helloEs){
      helloEsMapper.saveData(helloEs);
    return "success";
  }

  @GetMapping("getById")
  @JamesBao(type = 1,desc = "getById")
  public HelloEs getById(String id){
    HelloEs byId = helloEsMapper.getById(id);
    return byId;
  }

  @GetMapping("search")
  @JamesBao(type = 1,desc = "getListxx")
    public List<HelloEs> getListxx(String title,@PageableDefault(page = 1, value = 10) Pageable pageable){

    QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", title);

    //如果实体和数据的名称对应就会自动封装，pageable分页参数
    Iterable<HelloEs> listIt =  helloEsMapper.search(queryBuilder,pageable);

    ArrayList<HelloEs> helloEs = Lists.newArrayList(listIt);
    return helloEs;

  }
}
