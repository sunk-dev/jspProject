package action;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import beans.Board;
import db.BoardDAO;

public class BoardModifyAction implements Action {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int board_num=Integer.parseInt(request.getParameter("board_num"));
		String pass=request.getParameter("BOARD_PASS");
		Board article= new Board();
		Connection con =  getConnection();
		BoardDAO dao =  BoardDAO.getInstance();
		dao.setCon(con);
		boolean isWrite=dao.isArticleBoardWriter(board_num, pass);
		if(isWrite) {
			
			article.setBOARD_NUM(board_num);
			article.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			article.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			
			int upCount=dao.updateArcitle(article);
			System.out.println("upcount"+upCount);
			if(upCount==1) {
				commit(con);
				//request.setAttribute("board_num", board_num);
				close(con);
				return true;
				
				
			}else {
				rollback(con);
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패!')");
				out.println("history.back();");
				out.println("</script>");
				
			}
			
			
			
		}else {
			close(con);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정권한이 없습니다! 비밀번호가 일치하는지 확인해주세요!')");
			out.println("history.back();");
			out.println("</script>");
			
		}
		close(con);
		
		
		/*
		
		String realFolder="";
		String saveFolder= "/boardUpload";
		ServletContext context = request.getServletContext();
		realFolder =  context.getRealPath(saveFolder);
		System.out.println(realFolder);
		int fileSize = 1024*1024*10;
		
		MultipartRequest multi =  new MultipartRequest(request, realFolder, fileSize, new DefaultFileRenamePolicy());
		Board bdBeans =  new Board();
		bdBeans.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
		bdBeans.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
		bdBeans.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
		bdBeans.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
		bdBeans.setBOARD_FILE(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		Connection con = getConnection();
		BoardDAO dao = BoardDAO.getInstance();
		int re = dao.updateArticle(bdBeans, con);
		if(re!=1){
			rollback(con);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			commit(con);		
		}		
		close(con);	
		
		*/
		return false;
	}

}
