package action;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBoard;
import beans.Board;
import db.AdminBoardDAO;
import db.BoardDAO;

public class AdminBoardDeleteForm implements Action {
	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		String page=request.getParameter("page");
		System.out.println("page"+page);
		AdminBoard article= null;
		Connection con =  getConnection();
		AdminBoardDAO dao =  AdminBoardDAO.getInstance();
		dao.setCon(con);
		
		
		article=dao.selectArticle(board_num);
		close(con);	
		
		request.setAttribute("page", page);
		request.setAttribute("board_num", board_num);
		
		return false;
	}


}
