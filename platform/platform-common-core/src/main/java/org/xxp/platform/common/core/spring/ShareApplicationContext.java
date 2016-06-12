package org.xxp.platform.common.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 共享spring容器配置，使common公共jar包可以使用spring配置
 * 
 * @author xiping xing
 *
 *         Created on 2016年6月9日
 *
 */
public class ShareApplicationContext implements ApplicationContextAware {

  private static ApplicationContext context;
  
  public ShareApplicationContext(){
    
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationcontext) throws BeansException {
    context = applicationcontext;
  }

  public synchronized static Object getBean(String beanName) {
    return context.getBean(beanName);
  }

}
