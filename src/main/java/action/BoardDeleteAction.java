package action;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBoard;
import beans.Board;
import db.AdminBoardDAO;
import db.BoardDAO;

public class BoardDeleteAction implements Action {
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		
		String pass = request.getParameter("BOARD_PASS");
		String page=request.getParameter("page");
		System.out.println("page"+page);
		Board article= null;
		Connection con =  getConnection();
		BoardDAO dao =  BoardDAO.getInstance();
		dao.setCon(con);
		
		
		int delete=dao.deleteArticle(board_num,pass);
		if(delete==1) {
			
			commit(con);
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('암호오류! 암호를 확인해주세요!'); history.go(-1);</script>");
		    out.flush();
		    response.flushBuffer();
		    out.close();
			rollback(con);
		}
		close(con);	
		
		request.setAttribute("page", page);
		request.setAttribute("article", article);
		
		return false;
	}

}
