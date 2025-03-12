package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
	
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public void delete(String memberId);
	public MemberVO findByPrimaryKey(String memberId);
	public List<MemberVO> getAll();
	

}
