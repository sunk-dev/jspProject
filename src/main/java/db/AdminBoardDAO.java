package db;
import java.sql.*;
import java.util.ArrayList;

import javax.sql.DataSource;
import beans.Board;
import static db.JdbcUtil.*;

public class AdminBoardDAO {
	DataSource ds;
	Connection con;
	private static AdminBoardDAO boardDAO;
	
	private AdminBoardDAO() { }
	public static AdminBoardDAO getInstance() {//싱글톤(singleton) 객체
		if(boardDAO == null)
			boardDAO = new AdminBoardDAO();
		return boardDAO;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}
	
	
	//게시글 수정
	public int updateArticle(Board board) {
		
		PreparedStatement ps = null;
		int upcount=0;
		String sql = "update adminboard set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=? ";
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getBOARD_SUBJECT());
			ps.setString(2, board.getBOARD_CONTENT());
			ps.setInt(3, board.getBOARD_NUM());
			upcount=ps.executeUpdate();
			
		}catch(Exception e){
			System.out.println("글 수정중 오류");
			e.printStackTrace();
			
		}finally {
			close(ps);
			
		}
		
		
		return upcount;
		
		
	}
	
//전체 게시글 수 기져오기
//글의 개수 구하기.
	public int selectListCount() {
		int listCount= 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{			
			ps=con.prepareStatement("select count(*) from adminboard");
			rs = ps.executeQuery();
			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(ps);
		}
		return listCount;
	}
	
	public ArrayList<Board> selectArticleList(int page, int limit){
		ArrayList<Board> aList =  new ArrayList<Board>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from  adminboard order by BOARD_RE_REF desc, BOARD_RE_LEV asc, BOARD_RE_SEQ asc limit ?, ?";
		Board boardBean = null; 
		int startRow = (page-1)*10;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);	
			ps.setInt(2, limit);
			rs = ps.executeQuery();
			while(rs.next()){
				boardBean = new Board();
				boardBean.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				boardBean.setBOARD_NAME(rs.getString("BOARD_NAME"));	
				boardBean.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));	
				boardBean.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));	
				boardBean.setBOARD_FILE(rs.getString("BOARD_FILE"));	
				boardBean.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				boardBean.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				boardBean.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				boardBean.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				boardBean.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				aList.add(boardBean);
			} //while
		}//try
		catch(Exception e) {
			System.out.println("게시글 가져오기 오류");
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(ps);
		}	
		return aList;		
	}

	
	//상세게시글 한개 조회
	
	
	public Board selectArticle(int board_num) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from  adminboard where BOARD_NUM=? ";
		Board boardBean = null; 
		try
		{	ps = con.prepareStatement(sql);
			ps.setInt(1, board_num);
			rs = ps.executeQuery();
			if(rs.next()) {
				boardBean=new Board();
				boardBean.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				boardBean.setBOARD_NAME(rs.getString("BOARD_NAME"));	
				boardBean.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));	
				boardBean.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));	
				boardBean.setBOARD_FILE(rs.getString("BOARD_FILE"));	
				boardBean.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				boardBean.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				boardBean.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				boardBean.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				boardBean.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				
				
				
			}
		}catch(Exception e){
			System.out.println("상세정보조회 오류");
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return boardBean;
		
		
		
		
	}
	
	//조회수 업데이트
	
	public int updatReadCount(int board_num) {
		

		PreparedStatement ps = null;
		int upcount=0;
		String sql = "update adminboard set BOARD_READCOUNT=BOARD_READCOUNT+1 where BOARD_NUM=? ";
		try {
			
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_num);
			upcount=ps.executeUpdate();
			System.out.println("조회수업데이트 성공");
			
			
		}catch(Exception e){
			System.out.println("조회수 업데이트 오류");
			e.printStackTrace();
			
		}finally {
			
		}
		
		
		return upcount;
		
	}
	
	
	
	
	//게시글 등록 메서드
	public int insertArticle(Board b, Connection con) {
		int re=0;
		PreparedStatement ps = null;
		String sql="";
		ResultSet rs = null;
		int num = 0;
		sql = "select max(BOARD_NUM) from adminboard";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if(rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;
		
			System.out.println("num= " + num);
			sql = "insert into adminboard(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE) values(?,?,?,?,?,?,?,?,?,now())";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, b.getBOARD_NAME());
			ps.setString(3, b.getBOARD_PASS());
			ps.setString(4, b.getBOARD_SUBJECT());
			ps.setString(5, b.getBOARD_CONTENT());
			ps.setString(6, b.getBOARD_FILE());
			ps.setInt(7, num);
			ps.setInt(8, 0);
			ps.setInt(9, 0);		
			re = ps.executeUpdate();			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(ps);
		}
		return re;			
	}
	
	//
	public Boolean isArticleBoardWriter(int board_num ,String password) {
		
		
		Boolean isWriter=false;
		
		PreparedStatement ps = null;
		String sql="";
		ResultSet rs = null;
		sql = "select BOARD_PASS from adminboard where board_num=?";
		try
		{	ps = con.prepareStatement(sql);
			ps.setInt(1, board_num);
			rs = ps.executeQuery();
			rs.next();
			if(rs.getString(1).equals(password)) {
				isWriter=true;
			}
		}catch(Exception e){
			System.out.println("비밀번호 비교 오류");
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return isWriter;
		
		
	}
	
	//게시글 수정
	
	
	
	
}
