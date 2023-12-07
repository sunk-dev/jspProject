package action;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBoard;
import beans.Board;
import beans.PageInfo;
import db.AdminBoardDAO;
import db.BoardDAO;

public class AdminBoardListAction implements Action {

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<AdminBoard> articleList = new ArrayList<AdminBoard>();
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Connection con =  getConnection();
		AdminBoardDAO dao =  AdminBoardDAO.getInstance();
		dao.setCon(con);
		int listCount = dao.selectListCount();
		
		articleList = dao.selectArticleList(page, limit);
		int maxPage =  (int)Math.ceil((double)listCount/limit);
		int startPage = (((int)((double)page/limit + 0.9))-1)*10+1; 
		int endPage =  startPage+9;
		
		if(endPage > maxPage) endPage = maxPage;
		PageInfo pInfo =  new PageInfo();
		pInfo.setListCount(listCount); 
		pInfo.setPage(page);
		pInfo.setStartPage(startPage);
		pInfo.setEndPage(endPage);
		pInfo.setMaxPage(maxPage);
		
		request.setAttribute("pageInfo", pInfo);
		request.setAttribute("articleList", articleList);
		
		return false;
	}

}
