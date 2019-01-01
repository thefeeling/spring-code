package com.adminweb.admin;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adminweb.model.domain.Member;

@Repository
public class AdminRepository {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public String index(){
		return sqlSession.selectOne("com.adminweb.common.dual");
	}
	
	public int memberInsert(Member member){
		return sqlSession.insert("com.adminweb.common.memberInsert", member);
	}
	
	
	
}
