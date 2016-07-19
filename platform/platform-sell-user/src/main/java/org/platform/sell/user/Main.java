package org.platform.sell.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
  
  public static void main(String[] args){
    GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.setValidating(false);
    context.load("classpath*:spring/applicationContext.xml");
    context.refresh();
  }

}
