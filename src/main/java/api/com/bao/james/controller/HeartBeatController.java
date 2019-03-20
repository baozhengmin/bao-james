package api.com.bao.james.controller;

import api.com.bao.james.Exception.JamesBaoException;
import api.com.bao.james.aspect.JamesBao;
import api.com.bao.james.aspect.ValidParam;
import api.com.bao.james.dto.ActivityEventInfoDTO;
import api.com.bao.james.entity.ActivityEventInfoDo;
import api.com.bao.james.entity.response.JamesResponse;
import api.com.bao.james.service.IActivityEventInfoService;
import api.com.bao.james.utils.BeanUtil;
import api.com.bao.james.utils.ValidationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Slf4j
public class HeartBeatController {

  @ApiOperation(notes = "method heartBeat" ,value = " project James Bao HeartBeat")
  @GetMapping("ping")
  public String heartBeat(){

    return "pong";
  }

  @Autowired
  private IActivityEventInfoService activityEventInfoService;

  @ApiOperation(notes = "test mybatis" ,value = " test mybatis")
  @PostMapping("test/mybatis")
  @JamesBao(type = 1 , desc = "save antity")
  @ValidParam
  public JamesResponse testMybatis(@RequestBody ActivityEventInfoDTO activityEventInfoDTO){
    log.info("++++++ call api test/mybatis ,requestBody is {}",activityEventInfoDTO);
  try{
    ValidationUtils.checkParams(activityEventInfoDTO);
    ActivityEventInfoDo activityEventInfoDo = BeanUtil.map(activityEventInfoDTO, ActivityEventInfoDo.class);
    activityEventInfoService.saveActivityEventInfo(activityEventInfoDo);
    log.info("+++++++++++++++++save entity");
  }catch (JamesBaoException e){
    return JamesResponse.error(e);
  }
    return JamesResponse.success();
  }
}
