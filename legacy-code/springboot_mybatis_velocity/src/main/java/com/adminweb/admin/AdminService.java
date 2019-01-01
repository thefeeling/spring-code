package com.adminweb.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adminweb.model.domain.Member;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	public String test(){
		return adminRepository.index();
	}
	
	@Transactional
	public int txTest(){
		try {
			Member member = new Member();
			member.setAge(20);
			member.setName("kschoi");
			adminRepository.memberInsert(member);
			
			this.txTestSub();
			//member = null;
			//adminRepository.memberInsert(member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		return 1;
	}
	
	public int txTestSub(){
		Member member = new Member();
		member = null;
		return adminRepository.memberInsert(member);		
	}
	
	
	
}
