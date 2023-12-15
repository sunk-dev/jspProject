package controller;
import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.*;
import db.MemberDAO;

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
		System.out.println("command"+command);
		
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
				int board_num=(int) request.getAttribute("board_num");
				int page=(int) request.getAttribute("page");
				path = "boardDetail.bo?board_num="+board_num+"&page="+page;
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		
		else if(command.equals("/boardDeleteForm.bo")){
			action=new BoardDeleteForm();
			try {
				redirect=action.execute(request, response);
				path="board/qna_board_delete.jsp";
				//path = "adminBoardList.bo";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		//AdminBoardDeletePro.bo
		else if(command.equals("/boardDeletePro.bo")) {
			action=new BoardDeleteAction();
			try {
				redirect=action.execute(request, response);
				
				path="boardList.bo";
				//path = "adminBoardList.bo";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
			
		}
		
		
		
		
		else if(command.equals("/AdminBoardList.bo")){
			action = new AdminBoardListAction();
			try {
				redirect =action.execute(request, response);
				path = "/board/admin_board_list.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		else if(command.equals("/AdminBoardWriteForm.bo")) {
			//
			//1.로그인 했는지 여부 
			// yes -계정이 admin인지 확인 
			//        yes - 수정 화면으로 넘어가기
			//        no -관리자 계정이아니라 삭제 못함띄우기 공지사항으로 돌아가가기
			//no - 로그인 하세요 출력하기 
			//action = new AdminBoardWriteProAction();
			
			
			HttpSession session= request.getSession();
			String id= (String)session.getAttribute("id");
			
			if(id==null) {
				System.out.println("로그인필요!");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 해주세요!')");
				out.println("history.back();");
				out.println("</script>");
			}else {
				
				Connection con =  getConnection();
				MemberDAO dao =  MemberDAO.getInstance();
				dao.setCon(con);
				
				boolean isAdmin = dao.isAdminBoardWriter(id);
				
				if(!isAdmin) {
					close(con);
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('관리자 계정이 아닙니다!!')");
					out.println("history.back();");
					out.println("</script>");
				}else {
					path ="/board/admin_board_write.jsp";	
					
				}
				
				
				
				/*action = new AdminBoardWriteProAction();
				try {
					redirect=action.execute(request, response);
					path = "boardList.bo";
				}catch(Exception e) {
					e.printStackTrace();
				}	   	
				System.out.println(action);*/
				
				//path ="/board/qna_board_write3.jsp";	
				System.out.println(command);
			}
			
		}
		else if(command.equals("/AdminBoardWritePro.bo")) {
			
			action = new AdminBoardWriteProAction();
			System.out.println(command);
			try {
				redirect = action.execute(request, response);
				path = "AdminBoardList.bo";				
			}catch(Exception e) {
				e.printStackTrace();
			}	
			
			
			
		}
		
		else if(command.equals("/AdminboardDetail.bo")){
			action = new AdminBoardDetailAction();
			try {
				redirect=action.execute(request, response);
				path = "/board/admin_board_view.jsp";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		
		
		else if(command.equals("/AdminBoardModifyForm.bo")){
			action = new AdminBoardModifyFormAction();
			HttpSession session= request.getSession();
			String id= (String)session.getAttribute("id");
			
			if(id==null) {
				System.out.println("로그인필요!");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 해주세요!')");
				out.println("history.back();");
				out.println("</script>");
			}else {
				
				Connection con =  getConnection();
				MemberDAO dao =  MemberDAO.getInstance();
				dao.setCon(con);
				
				boolean isAdmin = dao.isAdminBoardWriter(id);
				
				if(!isAdmin) {
					close(con);
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('관리자 계정이 아닙니다!!')");
					out.println("history.back();");
					out.println("</script>");
				}else {
					try {
						redirect=action.execute(request, response);
						path = "/board/admin_board_modify.jsp";
					}catch(Exception e) {
						e.printStackTrace();
					}	   	
					
				}
			
			
					
		}}
		
		//AdminbBoardModifyPro.bo
		
		else if(command.equals("/AdminbBoardModifyPro.bo")){
			action = new AdminBoardModifyProAction();
			try {
				redirect=action.execute(request, response);
				path = "AdminBoardList.bo";
			}catch(Exception e) {
				e.printStackTrace();
			}	   		
		}
		//AdminboardDeleteForm.bo
		
		else if(command.equals("/AdminboardDeleteForm.bo")){
			action=new AdminBoardDeleteForm();
			HttpSession session= request.getSession();
			String id= (String)session.getAttribute("id");
			
			if(id==null) {
				System.out.println("로그인필요!");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 해주세요!')");
				out.println("history.back();");
				out.println("</script>");
			}else {
				
				Connection con =  getConnection();
				MemberDAO dao =  MemberDAO.getInstance();
				dao.setCon(con);
				
				boolean isAdmin = dao.isAdminBoardWriter(id);
				
				if(!isAdmin) {
					close(con);
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('관리자 계정이 아닙니다!! 삭제진행이 불가합니다')");
					out.println("history.back();");
					out.println("</script>");
				}else {
					try {
						redirect=action.execute(request, response);
						path="board/admin_board_delete2.jsp";
						//path = "adminBoardList.bo";
					}catch(Exception e) {
						e.printStackTrace();
					}	   		
					
				}
			
			
			
			
		}}
		//AdminBoardDeletePro.bo
		else if(command.equals("/AdminBoardDeletePro.bo")) {
			action=new AdminBoardDeleteAction();
			try {
				redirect=action.execute(request, response);
				path="AdminBoardList.bo";
				//path = "adminBoardList.bo";
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
