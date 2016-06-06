package org.platform.sell.user;

import org.platform.sell.user.service.TestService;
import org.platform.sell.user.service.impl.TestServiceImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
  
  public static void main(String[] args) throws InterruptedException{
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();  
    context.setValidating(false);  
    context.load("classpath*:spring/applicationContext.xml");  
    context.refresh(); 
    
    TestService t = context.getBean(TestServiceImpl.class);
    
    t.getTest();
    
    while(true){
      synchronized (TestMain.class) {
        Thread.sleep(1000l);
      }
    }
  }

}
