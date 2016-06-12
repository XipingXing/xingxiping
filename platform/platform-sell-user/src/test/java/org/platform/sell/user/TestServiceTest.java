package org.platform.sell.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.platform.sell.user.service.TestService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TestServiceTest {

  @Resource(name = "testService")
  private TestService testService;

  @Test
  public void getTest() {
    testService.getTest(1);
  }

  @Test
  public void getTest1() {
    testService.getTest(1);
  }

}
