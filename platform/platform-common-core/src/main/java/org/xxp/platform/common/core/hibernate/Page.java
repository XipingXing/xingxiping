package org.xxp.platform.common.core.hibernate;

import java.util.List;

/**
 * 分页数据实体 
 * 
 * @author xiping xing
 *
 * Created on 2016年6月8日
 *
 */
public class Page<T> {
  
  private int indexPage; //当前页
  
  private int pageSize; //每页条数
  
  private long count;//总条数
  
  private T entityParam; //数据查询条件实体
  
  private List<T> entityList; //数据结果集
  
  public Page(){
    
  }
  
  public Page(int indexPage, int pageSize){
    this.indexPage = indexPage;
    this.pageSize = pageSize;
  }
  
  public Page(int indexPage, int pageSize, Class<T> type){
    this.indexPage = indexPage;
    this.pageSize = pageSize;
    try {
      this.entityParam = type.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  public int getIndexPage() {
    return indexPage;
  }

  public void setIndexPage(int indexPage) {
    this.indexPage = indexPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public T getEntityParam() {
    return entityParam;
  }

  public void setEntityParam(T entityParam) {
    this.entityParam = entityParam;
  }

  public List<T> getEntityList() {
    return entityList;
  }

  public void setEntityList(List<T> entityList) {
    this.entityList = entityList;
  }

}
