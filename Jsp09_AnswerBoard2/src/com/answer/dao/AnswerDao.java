package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;

import common.JDBCTemplate;
public class AnswerDao extends JDBCTemplate{
	
	/*
	 * 관리자기능(ADMIN)
	 * 1.회원 전체 정보 (탈퇴 회원 포함)
	 * 2.가입된 회원(MYENABLED='Y')의 정보(탈퇴 회원 미포함)
	 * 3.회원 등급 조정
	 */
	
	public List<AnswerDto> userAll(){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AnswerDto> res = new ArrayList<AnswerDto>();
		
		String sql = " SELECT * FROM MYMEMBER ORDER BY MYNO DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				AnswerDto dto = new AnswerDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
				
				res.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return res;
	}
	
		//가입된 회원(MYENABLED='Y')의 정보(탈퇴 회원 미포함)
		public List<AnswerDto> selectEnabled(){
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<AnswerDto> res = new ArrayList<AnswerDto>();
			
			String sql = "SELECT * FROM MYMEMBER WHERE MYENABLED = 'Y' ORDER BY MYNO DESC";
			
			try {
				pstm = con.prepareStatement(sql);
				rs = pstm.executeQuery();
				
				while(rs.next()) {
					AnswerDto dto = new AnswerDto();
					dto.setMyno(rs.getInt(1));
					dto.setMyid(rs.getString(2));
					dto.setMypw(rs.getString(3));
					dto.setMyname(rs.getString(4));
					dto.setMyaddr(rs.getString(5));
					dto.setMyphone(rs.getString(6));
					dto.setMyemail(rs.getString(7));
					dto.setMyenabled(rs.getString(8));
					dto.setMyrole(rs.getString(9));
					
					res.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			
			return res;
		}
	
	
	

		//로그인
		public AnswerDto login(String id, String pw) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			AnswerDto res = new AnswerDto();
			
			String sql = "SELECT * FROM MYMEMBER WHERE MYID = ? AND MYPW = ? AND MYENABLED = ? ";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, id);
				pstm.setString(2, pw);
				pstm.setString(3, "Y");
				
				rs = pstm.executeQuery();
				
				while(rs.next()) {
					res.setMyno(rs.getInt(1));
					res.setMyid(rs.getString(2));
					res.setMypw(rs.getString(3));
					res.setMyname(rs.getString(4));
					res.setMyaddr(rs.getString(5));
					res.setMyphone(rs.getString(6));
					res.setMyemail(rs.getString(7));
					res.setMyenabled(rs.getString(8));
					res.setMyrole(rs.getString(9));
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			return res;
		}
		
		//아이디 중복체크
		public String idChk(String id) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			String res = null;
			
			String sql = "SELECT * FROM MYMEMBER WHERE MYID = ? ";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, id);
				
				rs = pstm.executeQuery();
				
				while(rs.next()) {
					res = rs.getString(2);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			return res;
		}
		
		//회원가입
		public int insertUser(AnswerDto dto) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = " INSERT INTO MYMEMBER VALUES(MYNOSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'Y','USER') ";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, dto.getMyid());
				pstm.setString(2, dto.getMypw());
				pstm.setString(3, dto.getMyname());
				pstm.setString(4, dto.getMyaddr());
				pstm.setString(5, dto.getMyphone());
				pstm.setString(6, dto.getMyemail());
				
				res = pstm.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con);
			}
			return res;
		}
		
		//내 정보 조회
		public AnswerDto selectUser(int myno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			AnswerDto res = new AnswerDto();
			
			String sql = "SELECT * FROM MYMEMBER WHERE MYNO = ?";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, myno);
				
				rs = pstm.executeQuery();
				while(rs.next()) {
					res.setMyno(rs.getInt(1));
					res.setMyid(rs.getString(2));
					res.setMypw(rs.getString(3));
					res.setMyname(rs.getString(4));
					res.setMyaddr(rs.getString(5));
					res.setMyphone(rs.getString(6));
					res.setMyemail(rs.getString(7));
					res.setMyenabled(rs.getString(8));
					res.setMyrole(rs.getString(9));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return res;
		}
		
		
		
		
		
	
		public List<AnswerDto> selectAll(){
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<AnswerDto> res = new ArrayList<AnswerDto>();
			
			String sql = "SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ";
			
			try {
				pstm = con.prepareStatement(sql);
				
				rs = pstm.executeQuery();
				
				while(rs.next()) {
					AnswerDto dto = new AnswerDto();
					dto.setBoardno(rs.getInt(1));
					dto.setGroupno(rs.getInt(2));
					dto.setGroupsq(rs.getInt(3));
					dto.setTitletab(rs.getInt(4));
					dto.setTitle(rs.getString(5));
					dto.setContent(rs.getString(6));
					dto.setWriter(rs.getString(7));
					dto.setRegdate(rs.getDate(8));
					dto.setIsdel(rs.getString(9));
					dto.setParentno(rs.getInt(10));
					
					res.add(dto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return res;
		}
		
		public AnswerDto selectOne(int boardno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			AnswerDto res = new AnswerDto();
			String sql = "SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? ";
						
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, boardno);
				
				rs = pstm.executeQuery();
				while(rs.next()) {
					res.setBoardno(rs.getInt(1));
					res.setGroupno(rs.getInt(2));
					res.setGroupsq(rs.getInt(3));
					res.setTitletab(rs.getInt(4));
					res.setTitle(rs.getString(5));
					res.setContent(rs.getString(6));
					res.setWriter(rs.getString(7));
					res.setRegdate(rs.getDate(8));
					res.setIsdel(rs.getString(9));
					res.setParentno(rs.getInt(10));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			
			return res;
		}
		
		public boolean insert(AnswerDto dto) {
			
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = "INSERT INTO ANSWERBOARD VALUES(BOARDNOSQ.NEXTVAL, GROUPNOSQ.NEXTVAL, 1, 0, ?, ?, ?, SYSDATE,'N',0)";

			try {
				pstm = con.prepareStatement(sql);
				
				pstm.setString(1, dto.getTitle());
				pstm.setString(2, dto.getContent());
				pstm.setString(3, dto.getWriter());
				
				res = pstm.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con);
			}
			
			
			return (res > 0)?true:false;
		}
		
		public boolean delete(int boardno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
//			String sql = "DELETE FROM ANSWERBOARD WHERE BOARDNO = ?";
			String sql = "UPDATE ANSWERBOARD SET ISDEL='Y' WHERE BOARDNO = ?";
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, boardno);
				
				res = pstm.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con);
			}
			
			
			return (res > 0)? true:false;
		}
		
		
		public boolean answerDel(int boardno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = "UPDATE ANSWERBOARD SET ISDEL='N' WHERE BOARDNO IN (SELECT PARENTNO FROM ANSWERBOARD WHERE PARENTNO = ?) ";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, boardno);
				
				res = pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con);
			}
			return (res>0)?true:false;
		}
		
		public boolean update(AnswerDto dto) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = "UPDATE ANSWERBOARD SET TITLE = ?, CONTENT = ? WHERE BOARDNO = ? ";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, dto.getTitle());
				pstm.setString(2, dto.getContent());
				pstm.setInt(3, dto.getBoardno());
				
				res = pstm.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
			}
			
			return (res>0)?true:false;
		}
		
		
		
		
		public int updateAnswer(int groupno, int groupsq) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = "UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ+1 WHERE GROUPNO = ? AND GROUPSQ > ? ";
			
//			String sql2 = "UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ+1\r\n" + 
//					" WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD\r\n" + 
//					" 				  WHERE BOARDNO=?) AND\r\n" + 
//					" 	   GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD\r\n" + 
//					" 	   			  WHERE BOARDNO=?)";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, groupno);
				pstm.setInt(2, groupsq);
				System.out.println("03.query 준비: " +sql);
				
				res = pstm.executeUpdate();
				
				if(res>0) {
					commit(con);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con);
			}
			return res;
		}
		
		public int insertAnswer(AnswerDto dto) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = "INSERT INTO ANSWERBOARD VALUES(BOARDNOSQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,'N',?)";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, dto.getGroupno());
				pstm.setInt(2, dto.getGroupsq()+1);
				pstm.setInt(3, dto.getTitletab()+1);
				pstm.setString(4, dto.getTitle());
				pstm.setString(5, dto.getContent());
				pstm.setString(6, dto.getWriter());
				pstm.setInt(7, dto.getParentno());
				
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con);
			}
			return res;
		}
		
		
		
	
}
