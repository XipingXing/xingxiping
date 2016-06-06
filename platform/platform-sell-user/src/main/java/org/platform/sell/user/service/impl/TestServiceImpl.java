package org.platform.sell.user.service.impl;

import javax.annotation.Resource;

import org.platform.sell.user.dao.TestDao;
import org.platform.sell.user.model.TestModel;
import org.platform.sell.user.service.TestService;
import org.springframework.stereotype.Service;

@Service(value="testService")
public class TestServiceImpl implements TestService{
  
  @Resource(name="testDao")
  private TestDao testDao;

  @Override
  public TestModel getTest() {
    return testDao.get();
  }

}
