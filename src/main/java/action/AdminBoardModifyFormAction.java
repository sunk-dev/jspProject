package action;

import static db.JdbcUtil.*;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBoard;
import beans.Board;
import db.AdminBoardDAO;
import db.BoardDAO;

public class AdminBoardModifyFormAction implements Action{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		AdminBoard article = null;
		Connection con =  getConnection();
		AdminBoardDAO dao =  AdminBoardDAO.getInstance();
		dao.setCon(con);
		article = dao.selectArticle(board_num);
		close(con);
		
		request.setAttribute("article",article);
		return false;
	}

}
