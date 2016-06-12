package org.xxp.platform.common.core.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

  /**
   * 根据主键id查询数据
   * 
   * @param id
   * @return
   */
  public T getById(Serializable id);

  /**
   * 新增
   * 
   * @param t
   * @return
   */
  public int save(T t);

  /**
   * 修改
   * 
   * @param t
   */
  public void update(T t);

  /**
   * 删除
   * 
   * @param t
   */
  public void delete(T t);

  /**
   * 删除（主键）
   * 
   * @param id
   */
  public void deleteById(Serializable id);

  /**
   * 根据实体属性值查询数据集合（不分页）
   * 
   * @param t
   * @return
   */
  public List<T> getByColumn(T t);

  /**
   * 根据page里面的实体属性值查询数据集合（分页）
   * 
   * @param page
   * @return
   */
  public Page<T> getPageByColumn(Page<T> page);

  /**
   * 模糊查询
   * 
   * @param names
   *          要进行模糊的字段对象名
   * @param values
   *          模糊值
   * @return
   */
  public List<T> getByLike(String[] names, String[] values);

  /**
   * 模糊查询（分页）
   * 
   * @param names
   *          要进行模糊的字段对象名
   * @param values
   *          模糊值
   * @return
   */
  public Page<T> getPageByLike(String[] names, String[] values, Page<T> page);

  /**
   * 执行hql查询，根据map对hql参数赋值
   * 
   * @param hqlString
   * @param params
   * @return
   */
  public List<T> queryByHql(String hqlString, Map<String, Object> params);

}
