package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.*;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean redirect = false;
		Action action = null;
		String path=null;
		request.setCharacterEncoding("utf-8");
		String requestURI = request.getRequestURI();						
		int i = requestURI.lastIndexOf('/');	
		String command = requestURI.substring(i);		
		
		if(command.equals("/boardWriteForm.bo")) {
			path ="/board/qna_board_write3.jsp";	
			System.out.println(command);
		}
		
		else if(command.equals("/boardWritePro.bo")) { //boardWritePro.bo
			action = new BoardWriteProAction();
			System.out.println(command);
			try {
				redirect = action.execute(request, response);
				path = "boardList.bo";				
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}		
		else if(command.equals("/boardList.bo")){
			action = new BoardListAction();
			try {
				redirect =action.execute(request, response);
				path = "/board/qna_board_list.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		else if(command.equals("/boardDetail.bo")){
			action = new BoardDetailAction();
			try {
				redirect=action.execute(request, response);
				path = "/board/qna_board_view.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		else if(command.equals("/boardModifyForm.bo")){
			action = new BoardModifyFormAction();
			try {
				redirect=action.execute(request, response);
				path = "/board/qna_board_modify2.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		else if(command.equals("/boardModifyPro.bo")){
			action = new BoardModifyProAction();
			try {
				redirect=action.execute(request, response);
				path = "boardList.bo";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		else if(command.equals("/adminBoardList.bo")){
			action = new AdminBoardListAction();
			try {
				redirect =action.execute(request, response);
				path = "/board/admin_board_list.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		
		//////// 해당페이지 이동~~
		if(redirect==true)
			response.sendRedirect(path);
		else {
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}
			
		
	}
}
