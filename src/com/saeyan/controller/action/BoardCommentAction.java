package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.CommentDAO;
import com.saeyan.dto.CommentVO;

public class BoardCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userid");

		CommentVO cVo = new CommentVO();
		cVo.setNum(Integer.valueOf(num));
		cVo.setId(userId);
		cVo.setComment(request.getParameter("commentmemo"));
		CommentDAO cDao = CommentDAO.getInstance();
		cDao.insertComment(cVo);

		new BoardViewAction().execute(request, response);
	}

}
