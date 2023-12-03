package action;

import java.io.PrintWriter;
import java.sql.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import beans.Board;
import db.BoardDAO;
import static db.JdbcUtil.*;

public class BoardWriteProAction implements Action {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
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
		int re = dao.insertArticle(bdBeans, con);
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
		return true;
	}		
}
