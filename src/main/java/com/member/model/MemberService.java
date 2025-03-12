package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemberService {
	
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String memberId, String memberName, byte[] memberImage, String email ,java.sql.Date birthday,
			Timestamp registrationTime, String phone, Integer accountStatus, String password) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemberId(memberId);
		memberVO.setMemberName(memberName);
		memberVO.setMemberImage(memberImage);
		memberVO.setEmail(email);
		memberVO.setRegistrationTime(registrationTime);
		memberVO.setPhone(phone);
		memberVO.setAccountStatus(accountStatus);
		memberVO.setPassword(password);
		memberVO.setBirthday(birthday);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(String memberId, String memberName, byte[] memberImage, String email ,java.sql.Date birthday,
			Timestamp registrationTime, String phone, Integer accountStatus, String password) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemberId(memberId);
		memberVO.setMemberName(memberName);
		memberVO.setMemberImage(memberImage);
		memberVO.setEmail(email);
		memberVO.setRegistrationTime(registrationTime);
		memberVO.setPhone(phone);
		memberVO.setAccountStatus(accountStatus);
		memberVO.setPassword(password);
		memberVO.setBirthday(birthday);
		dao.insert(memberVO);

		return memberVO;
	}

	public void deleteMember(String memberId) {
		dao.delete(memberId);
	}

	public MemberVO getOneEmp(String memberId) {
		return dao.findByPrimaryKey(memberId);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	

}
