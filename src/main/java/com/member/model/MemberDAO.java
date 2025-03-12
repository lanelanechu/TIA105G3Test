package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class MemberDAO implements MemberDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/life_space_01");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT =
			"INSERT INTO member (member_id, member_name, member_image, email, registration_time, phone, account_status, password, birthday) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT member_id, member_name, member_image, email, registration_time, phone, account_status, password, birthday FROM member order by member_id";
	private static final String GET_ONE_STMT =
			"SELECT member_id, member_name, member_image, email, registration_time, phone, account_status, password, birthday FROM member where member_id = ?";
	private static final String DELETE = 
			"DELETE FROM member where member_id = ?";
	private static final String UPDATE =
			"UPDATE member set member_name=?, member_image=?, email=?, registration_time=?, phone=?, account_status=?, password=?, birthday=? where member_id=?";
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memberVO.getMemberId());
			pstmt.setString(2, memberVO.getMemberName());
			pstmt.setBytes(3, memberVO.getMemberImage());
			pstmt.setString(4, memberVO.getEmail());
//			pstmt.setTimestamp(5, memberVO.getRegistrationTime());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setInt(7, memberVO.getAccountStatus());
			pstmt.setString(8, memberVO.getPassword());
			pstmt.setDate(9, memberVO.getBirthday());
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memberVO.getMemberId());
			pstmt.setString(2, memberVO.getMemberName());
//			照片啦QQ pstmt.setbyte[](3, memberVO.getMemberImage());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setTimestamp(5, memberVO.getRegistrationTime());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setInt(7, memberVO.getAccountStatus());
			pstmt.setString(8, memberVO.getPassword());
			pstmt.setDate(9, memberVO.getBirthday());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	
	@Override
	public void delete(String memberId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	
	
	@Override
	public MemberVO findByPrimaryKey(String memberId) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
;
			
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getString("member_id"));
				memberVO.setMemberName(rs.getString("member_name"));
				memberVO.setMemberImage(rs.getBytes("member_image"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRegistrationTime(rs.getTimestamp("registration_time"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setAccountStatus(rs.getInt("account_status"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setBirthday(rs.getDate("birthday"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return memberVO;
	}
	
	
	
	
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberId(rs.getString("member_id"));
				memberVO.setMemberName(rs.getString("member_name"));
				memberVO.setMemberImage(rs.getBytes("member_image"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setRegistrationTime(rs.getTimestamp("registration_time"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setAccountStatus(rs.getInt("account_status"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setBirthday(rs.getDate("birthday"));
				list.add(memberVO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	

	
}
