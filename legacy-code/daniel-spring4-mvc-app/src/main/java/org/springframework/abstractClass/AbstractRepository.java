package org.springframework.abstractClass;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractRepository {

	/***
	 * MyBatis Bean Autowired
	 */
	//@Autowired
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate getSqlSession() throws Exception{
		return this.sqlSession;
	}
}
