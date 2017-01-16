package com.ecej.nove.sms.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ecej.nove.dao.base.BaseDao;

@Repository
public class SmsBaseDao extends BaseDao {

	@Resource
	public void setSmsSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
