package org.xxp.platform.common.core.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

public abstract class HibernateBaseDao<T> extends HibernateBase<T> implements BaseDao<T> {

  @Override
  public T getById(Serializable id) {
    return hibernateTemplate.get(type, id);
  }

  @Override
  public int save(T t) {
    return (int) hibernateTemplate.save(t);
  }

  @Override
  public void update(T t) {
    hibernateTemplate.update(t);
  }

  @Override
  public void delete(T t) {
    hibernateTemplate.delete(t);
  }

  @Override
  public void deleteById(Serializable id) {
    T t = getById(id);
    if (t != null) {
      delete(t);
    }
  }

  @Override
  public List<T> getByColumn(T t) {
    return hibernateTemplate.findByExample(t);
  }

  @Override
  public Page<T> getPageByColumn(Page<T> page) {
    if (page.getIndexPage() <= 0 && page.getPageSize() <= 0) {
      page.setCount(0);
      page.setEntityList(new ArrayList<T>());
    } else {
      List<T> entityList = hibernateTemplate.findByExample(page.getEntityParam(),
          (page.getIndexPage() - 1) * page.getPageSize(), page.getPageSize());
      List<T> allEntityList = getByColumn(page.getEntityParam());
      page.setCount((allEntityList == null || allEntityList.isEmpty()) ? 0 : allEntityList.size());
      page.setEntityList(entityList);
    }
    return page;
  }

  @Override
  public List<T> getByLike(String[] names, String[] values) {
    if (names == null || values == null || names.length != values.length) {
      return null;
    }
    String tableName = type.getName().replace(type.getPackage().getName() + ".", "");
    StringBuffer hql = new StringBuffer(" from " + tableName + " " + tableName + " ");
    for (int i = 0; i < names.length; i++) {
      if (hql.indexOf("where") < 0) {
        hql.append(" where ");
      } else {
        hql.append(" and ");
      }
      hql.append(" " + tableName + "." + names[i] + " like " + " ? " + " ");
    }

    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(hql.toString());
    for (int i = 0; i < values.length; i++) {
      query.setParameter(i + 1, "%" + values[i] + "%");
    }
    return query.list();
  }

  @Override
  public Page<T> getPageByLike(String[] names, String[] values, Page<T> page) {
    if (names == null || values == null || names.length != values.length || page == null || page.getIndexPage() == 0
        || page.getPageSize() == 0) {
      return null;
    }
    String tableName = type.getName().replace(type.getPackage().getName() + ".", "");
    StringBuffer hql = new StringBuffer(" from " + tableName + " " + tableName + " ");
    for (int i = 0; i < names.length; i++) {
      if (hql.indexOf("where") < 0) {
        hql.append(" where ");
      } else {
        hql.append(" and ");
      }
      hql.append(" " + tableName + "." + names[i] + " like " + " ? " + " ");
    }

    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(hql.toString());
    for (int i = 0; i < values.length; i++) {
      query.setParameter(i + 1, "%" + values[i] + "%");
    }
    query.setFirstResult((page.getIndexPage() - 1) * page.getPageSize());
    query.setMaxResults(page.getPageSize());

    page.setEntityList(query.list());
    page.setCount(getByLike(names, values).size());
    return page;
  }

  @Override
  public List<T> queryByHql(String hqlString, Map<String, Object> params) {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery(hqlString);
    if (params != null && !params.isEmpty()) {
      Set<Entry<String, Object>> set = params.entrySet();
      for (Entry<String, Object> en : set) {
        query.setParameter(en.getKey(), en.getValue());
      }
    }
    return query.list();
  }

}
