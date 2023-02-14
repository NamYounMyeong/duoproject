package com.kh.sdm.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.sdm.entity.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public String findPw(String memberId) {
		return sqlSession.selectOne("findPw", memberId);
	}
	
	//회원가입
	@Override
	public void join(MemberDto memberDto) {
		//비밀번호 암호화
		memberDto.setMemberPw(encoder.encode(memberDto.getMemberPw()));
		
		sqlSession.insert("member.join", memberDto);
	}
	
}