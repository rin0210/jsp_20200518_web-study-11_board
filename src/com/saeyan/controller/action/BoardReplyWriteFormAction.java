package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReplyWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		String grp = request.getParameter("grp");
		String seq = request.getParameter("seq");
		String lvl = request.getParameter("lvl");
		
		String url = "/board/replyWrite.jsp";
		request.setAttribute("num", num);
		request.setAttribute("grp", grp);
		request.setAttribute("seq", seq);
		request.setAttribute("lvl", lvl);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
