package api.com.bao.james.controller;

import api.com.bao.james.service.IActivityEventInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * $DESCRIPTION$
 *
 * @author : James Bao
 * @version :
 * @date : Created in 上午11:10$
 */
@RestController
@Api("this is heart beat controller!")
//@RequestMapping("xxx/")
public class HeartBeatController {

  @ApiOperation(notes = "method heartBeat" ,value = " project James Bao HeartBeat")
  @GetMapping("ping")
  public String heartBeat(){

    return "pong";
  }

  @Autowired
  private IActivityEventInfoService activityEventInfoService;

  @ApiOperation(notes = "test mybatis" ,value = " test mybatis")
  @GetMapping("test/mybatis")
  public String testMybatis(){

    return activityEventInfoService.getRecentlyEvent();
  }
}
