package org.xxp.platform.common.core.hibernate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

public abstract class HibernateBase<T> {

  @Autowired
  protected HibernateTemplate hibernateTemplate;

  @Autowired
  protected SessionFactory sessionFactory;

  protected Class<T> type;

  @SuppressWarnings("unchecked")
  public HibernateBase() {
    Type genType = getClass().getGenericSuperclass();
    if (genType != null && genType instanceof Type) {
      Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
      if (types != null && types.length > 0)
        type = (Class<T>) types[0];
    }
  }

  public HibernateTemplate getHibernateTemplate() {
    return hibernateTemplate;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

}
