package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.answer.dao.AnswerDao;
import com.answer.dto.AnswerDto;


@WebServlet("/answer.do")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AnswerController() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.printf("{%s}\n",command);
		
		AnswerDao dao = new AnswerDao();
		
		if(command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			AnswerDto dto = dao.login(id, pw);
			
			if(dto.getMyid() != null) {
				HttpSession session = request.getSession();

				session.setAttribute("dto",dto);
				session.setMaxInactiveInterval(60*60);
			
				if(dto.getMyrole().equals("ADMIN")) {
					response.sendRedirect("adminmain.jsp");
				}else if(dto.getMyrole().equals("USER")) {
					response.sendRedirect("usermain.jsp");
				}
			}else {
				jsResponse("login 실패", "index.jsp", response);
			}
			
		} else if(command.equals("userlistall")){
			List<AnswerDto> list = dao.userAll();
			request.setAttribute("list", list);
			dispatch("userlistall.jsp", request, response);
			
			
		} else if(command.equals("registform")) {
			response.sendRedirect("registform.jsp");
			
			
		} else if(command.equals("idchk")) {
			String myid = request.getParameter("id");
			String res = dao.idChk(myid);
			boolean idnotused = true;
			
			if(res != null) {
				idnotused = false;
			}
			response.sendRedirect("idchk.jsp?idnotused="+idnotused);
			
		} else if(command.equals("insertuser")) {
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			String myname = request.getParameter("mypw");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");
			
			AnswerDto dto = new AnswerDto(myid, mypw, myname, myaddr, myphone,myemail);
			int res = dao.insertUser(dto);
			
			if(res>0) {
				jsResponse("회원가입 성공", "index.jsp", response);
			} else {
				jsResponse("회원가입 실패", "index.jsp", response);
			}
			
			
		} else if(command.equals("userlistenabled")) {
			List<AnswerDto> list = dao.selectEnabled();
			request.setAttribute("list", list);
			dispatch("userlistenabled.jsp", request, response);
			
		} else if(command.equals("updateroleform")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			AnswerDto dto = dao.selectUser(myno);
			request.setAttribute("dto", dto);
			dispatch("updateroleform.jsp", request, response);
			
		} else if(command.equals("list")) {
			List<AnswerDto> list = dao.selectAll();
			
			request.setAttribute("list", list);
			dispatch("boardlist.jsp", request, response);
			
		} else if(command.equals("selectone")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = dao.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("boarddetail.jsp", request, response);
			
		} else if(command.equals("writeform")) {
			response.sendRedirect("boardwrite.jsp");
			
		} else if(command.equals("boardwrite")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			AnswerDto dto = new AnswerDto(title, content, writer);
			
			boolean res = dao.insert(dto);
			
			if(res) {
				jsResponse("글쓰기 성공", "answer.do?command=list", response);
			} else {
				jsResponse("글쓰기 실패", "answer.do?command=writeform", response);
			}
			
			
		} else if(command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			boolean res = dao.answerDel(boardno);
			
			if(res) {
				jsResponse("답글이 달려 삭제할 수 없습니다", "answer.do?command=list", response);
			}else {
			
			boolean res1 = dao.delete(boardno);
			
				if(res1) {
					jsResponse("삭제 성공", "answer.do?command=list", response);
				}else {
					jsResponse("삭제 실패", "answer.do?command=selectone&boardno="+boardno, response);
				}
			}
		} else if(command.equals("updateform")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = dao.selectOne(boardno);
			
			request.setAttribute("dto", dto);
			dispatch("update.jsp", request, response);
			
		} else if(command.equals("update")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			AnswerDto dto = new AnswerDto(boardno, title, content);
			
			boolean res = dao.update(dto);
			
			if(res) {
				jsResponse("수정 성공", "answer.do?command=selectone&boardno="+boardno, response);
			} else {
				jsResponse("수정 실패", "answer.do?command=updateform&boardno="+boardno, response);
			}
			
			
		} else if(command.equals("answerform")) {
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			
			AnswerDto dto = dao.selectOne(parentboardno);
			request.setAttribute("parent", dto);
			dispatch("answerwrite.jsp", request, response);
			
		} else if(command.equals("answerwrite")) {
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			
			// update groupsq
			AnswerDto parent = dao.selectOne(parentboardno);
			
			int parentgroupno = parent.getGroupno();
			int parentgroupsq = parent.getGroupsq();
			int parenttitletab = parent.getTitletab();
			String isdel = parent.getIsdel();
			int parentno = parentboardno;	
			
			int updateRes = dao.updateAnswer(parentgroupno, parentgroupsq);
			
			if(updateRes > 0 ) {
				System.out.println("순서 변경 성공");
			}else {
				System.out.println("순서 변경 실패 or 변경할 글 없음");
			}
			
			// insert
			AnswerDto dto = new AnswerDto(0,parentgroupno, parentgroupsq, parenttitletab, title, content, writer, null,isdel,parentno);
			
			int res = dao.insertAnswer(dto);
			if(res > 0) {
				response.sendRedirect("answer.do?command=list");
			}else {
				response.sendRedirect("answer.do?command=selectone&boardno="+parentboardno);
			}
			
		}
			
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"+
				"alert('"+msg+"');" +
				"location.href='"+ url + "';" +
				"</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
		
	}
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}
	
}
