package com.member.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/life_space_01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO member (member_id, member_name, member_image, email, registration_time, phone, account_status, password, birthday) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT member_id, member_name, member_image, email, registration_time, phone, account_status, password, birthday FROM member order by member_id";
	private static final String GET_ONE_STMT = "SELECT member_id, member_name, member_image, email, registration_time, phone, account_status, password, birthday FROM member where member_id = ?";
	private static final String DELETE = "DELETE FROM member where member_id = ?";
	private static final String UPDATE = "UPDATE member set member_name=?, member_image=?, email=?, phone=?, account_status=?, password=?, birthday=? where member_id=?";
	
	// 獲取下一個流水號
    private String getNextMemberId() throws SQLException {
        String nextId = "M001"; // 預設初始值
        String pref = "M";  // 改成你的表格的流水號開頭

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
        }

        String query = "SELECT MAX(member_id) FROM member";
        try (
            Connection conn = DriverManager.getConnection(url, userid, passwd);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
        ) {
            if (rs.next() && rs.getString(1) != null) {
                String currentId = rs.getString(1);
                int numericPart = Integer.parseInt(currentId.substring(1));
                numericPart++;
                nextId = pref + String.format("%03d", numericPart);
            }
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        }
        return nextId;
    }
	
	
	

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, getNextMemberId());
			pstmt.setString(2, memberVO.getMemberName());
			pstmt.setBytes(3, memberVO.getMemberImage());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setTimestamp(5, memberVO.getRegistrationTime());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setInt(7, memberVO.getAccountStatus());
			pstmt.setString(8, memberVO.getPassword());
			pstmt.setDate(9, memberVO.getBirthday());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMemberName());
			pstmt.setBytes(2, memberVO.getMemberImage());
			pstmt.setString(3, memberVO.getEmail());
//			pstmt.setTimestamp(4, memberVO.getRegistrationTime());
			pstmt.setString(4, memberVO.getPhone());
			pstmt.setInt(5, memberVO.getAccountStatus());
			pstmt.setString(6, memberVO.getPassword());
			pstmt.setDate(7, memberVO.getBirthday());
			pstmt.setString(8, memberVO.getMemberId());
			// 注意!!!!根據SQL語法getMemberId()應該是第 8 個

			int rowsUpdated = pstmt.executeUpdate();
			System.out.println("更新筆數: " + rowsUpdated);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberId);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

//----------------- 執行區 ---------------------------

	public static void main(String[] args) {

		MemberJDBCDAO dao1 = new MemberJDBCDAO();

		// 新增 時間java這邊還是要寫，照片允許null可以不要
		MemberVO memberVO1 = new MemberVO();
		//memberVO1.setMemberId("M007");
//		memberVO1.setMemberName("小南");
//		memberVO1.setEmail("4sdfdf56@gmail.com");
//		memberVO1.setRegistrationTime(Timestamp.valueOf(LocalDateTime.now()));
//		memberVO1.setPhone("0933343264");
//		memberVO1.setAccountStatus(1);
//		memberVO1.setPassword("abaf");
//		memberVO1.setBirthday(java.sql.Date.valueOf("1940-03-12"));
//		dao1.insert(memberVO1);
//		System.out.println("我完成了");

//		//修改
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setMemberId("M008");
		memberVO2.setMemberImage(null); //沒有照片就是null
		memberVO2.setMemberName("阿修ZZZ");
		memberVO2.setEmail("122dfsdfsf3@gmail.com");
		memberVO2.setRegistrationTime(Timestamp.valueOf(LocalDateTime.now()));
		memberVO2.setPhone("0912143565");
		memberVO2.setAccountStatus(1);
		memberVO2.setPassword("abc");
		memberVO2.setBirthday(java.sql.Date.valueOf("1980-09-12"));
		dao1.update(memberVO2);
		System.out.println("我完成了");
//		
//		//刪除
//		dao1.delete("M006");
//		System.out.println("我完成了");
//		
//		//查詢1
//		MemberVO memberVO3 = dao1.findByPrimaryKey("M005");
//		System.out.println(memberVO3.getMemberId() + ",");
//		System.out.println(memberVO3.getMemberName() + ",");
//		System.out.println(memberVO3.getMemberImage() + ",");
//		System.out.println(memberVO3.getEmail() + ",");
//		System.out.println(memberVO3.getRegistrationTime() + ",");
//		System.out.println(memberVO3.getPhone() + ",");
//		System.out.println(memberVO3.getAccountStatus() + ",");
//		System.out.println(memberVO3.getPassword() + ",");
//		System.out.println(memberVO3.getBirthday());
//		System.out.println("------------------");
//		
//		
//		//查詢2
		List<MemberVO> list = dao1.getAll();
		for (MemberVO aMember : list) {
			System.out.print(aMember.getMemberId() + ",");
			System.out.print(aMember.getMemberName() + ",");
			System.out.print(aMember.getMemberImage() + ",");
			System.out.print(aMember.getEmail() + ",");
			System.out.print(aMember.getRegistrationTime() + ",");
			System.out.print(aMember.getPhone() + ",");
			System.out.print(aMember.getAccountStatus() + ",");
			System.out.print(aMember.getPassword() + ",");
			System.out.print(aMember.getBirthday());
			System.out.println();
		}
		System.out.println("我完成了");

	}

}
