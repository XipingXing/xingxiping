package org.platform.sell.user.dubboServiceImpl;

import javax.annotation.Resource;

import org.platform.sell.dubboInterface.user.dubboService.TestDubboService;
import org.platform.sell.user.service.TestService;
import org.springframework.stereotype.Service;

@Service(value="testDubboService")
public class TestDubboServiceImpl implements TestDubboService{
  
  @Resource(name="testService")
  private TestService testService;

}
