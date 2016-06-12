package org.platform.sell.user.dao.impl;

import org.platform.sell.user.dao.TestDao;
import org.platform.sell.user.model.TestModel;
import org.springframework.stereotype.Repository;
import org.xxp.platform.common.core.hibernate.HibernateBaseDao;

@Repository(value = "testDao")
public class TestDaoImpl extends HibernateBaseDao<TestModel> implements TestDao {

}
