package com.hanking.pr.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@SuppressWarnings("unchecked")
@Component
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

	/**
	 * 使用注解需要将setSqlSessionTemplate使用注解注入
	 */

	@Autowired
	private SqlSessionTemplate sqlSessionTemplateMysql;

	@Override
	public final SqlSession getSqlSession() {
		return this.sqlSessionTemplateMysql;
	}

	private static final String SQL_ID_ADD = ".add";
	private static final String SQL_ID_UPDATE = ".update";
	private static final String SQL_ID_DELETE = ".delete";
	private static final String SQL_ID_BY_ID = ".getById";
	private static final String SQL_ID_FIND = ".find";
	private static final String SQL_ID_GETCOUNT = ".getCount";

	protected Class<T> clazz;
	protected boolean flag = true;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public int add(T t) {
		int result = this.getSqlSession().insert(clazz.getName() + SQL_ID_ADD,
				t);
		return result;
	}

	@Override
	public int delete(Map<String, Object> map) {
		return this.getSqlSession()
				.delete(clazz.getName() + SQL_ID_DELETE, map);
	}

	@Override
	public T getModelById(Map<String, Object> map) {
		return this.getSqlSession().selectOne(clazz.getName() + SQL_ID_BY_ID,
				map);
	}

	// 用完删除
	@Override
	public int delete(int id) {
		return this.getSqlSession().delete(clazz.getName() + SQL_ID_DELETE, id);
	}

	@Override
	public T getModelById(int id) {
		return this.getSqlSession().selectOne(clazz.getName() + SQL_ID_BY_ID,
				id);
	}

	@Override
	public int update(T t) {
		return this.getSqlSession().update(clazz.getName() + SQL_ID_UPDATE, t);
	}

	public int getCount(String queryStr) {
		return this.getSqlSession().selectOne(
				clazz.getName() + SQL_ID_GETCOUNT, queryStr);
	}

/*	public PageBean<T> find(PageBean<T> pageBean) {
		int totalRecord = this.getSqlSession().selectOne(
				clazz.getName() + SQL_ID_GETCOUNT, pageBean);
		// System.out.println("当前的totalRecord为:" + totalRecord);
		if (pageBean.getCurrentPageNum() == 0) {
			pageBean.setCurrentPageNum(1);
		}
		pageBean.setTotalRecord(totalRecord);
		List<T> datas = this.getSqlSession().selectList(
				clazz.getName() + SQL_ID_FIND, pageBean);
		pageBean.setDatas(datas);
		return pageBean;
	}
	
	public PageBean<T> findBySql(PageBean<T> pageBean,Map<String,String>params) {
		int totalRecord = this.getSqlSession().selectOne(
				clazz.getName() + params.get("count"), pageBean);
		// System.out.println("当前的totalRecord为:" + totalRecord);
		if (pageBean.getCurrentPageNum() == 0) {
			pageBean.setCurrentPageNum(1);
		}
		pageBean.setTotalRecord(totalRecord);
		List<T> datas = this.getSqlSession().selectList(
				clazz.getName() + params.get("find"), pageBean);
		pageBean.setDatas(datas);
		return pageBean;
	}*/

	// 用完删除
	@Override
	public int delete(T t) {
		return this.getSqlSession().delete(clazz.getName() + SQL_ID_DELETE, t);
	}

	@Override
	protected void checkDaoConfig() {
		// Assert.notNull(getSqlSession(),
		// "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}

}
