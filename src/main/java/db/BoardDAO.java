package db;
import java.sql.*;
import java.util.ArrayList;

import javax.sql.DataSource;
import beans.Board;
import static db.JdbcUtil.*;

public class BoardDAO {
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() { }
	public static BoardDAO getInstance() {//싱글톤(singleton) 객체
		if(boardDAO == null)
			boardDAO = new BoardDAO();
		return boardDAO;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}
//전체 게시글 수 기져오기
//글의 개수 구하기.
	public int selectListCount() {
		int listCount= 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{			
			ps=con.prepareStatement("select count(*) from board");
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
		String sql = "select * from  board order by BOARD_RE_REF desc, BOARD_RE_LEV asc, BOARD_RE_SEQ asc limit ?, ?";
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

	
	
	
	
	
	//게시글 등록 메서드
	public int insertArticle(Board b, Connection con) {
		int re=0;
		PreparedStatement ps = null;
		String sql="";
		ResultSet rs = null;
		int num = 0;
		sql = "select max(BOARD_NUM) from board";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if(rs.next())
				num = rs.getInt(1) + 1;
			else
				num = 1;
		
			System.out.println("num= " + num);
			sql = "insert into board(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_DATE) values(?,?,?,?,?,?,?,?,?,now())";
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
	
	
}
