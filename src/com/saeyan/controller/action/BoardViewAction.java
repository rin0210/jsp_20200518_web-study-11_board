package com.saeyan.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.CommentDAO;
import com.saeyan.dao.ReadCountDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.CommentVO;
import com.saeyan.dto.ReadCountVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		String num = request.getParameter("num");
		System.out.println("번호가 넘어왔니?" + num);

		ReadCountDAO rDao = ReadCountDAO.getInstance();
		BoardDAO bDao = BoardDAO.getInstance();
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userid");
		System.out.println("userName: " + userName);

		if (rDao.idChk(userName, Integer.valueOf(num)) == true) { // 아이디 체크
			bDao.updateReadCount(num);
			ReadCountVO rVo = new ReadCountVO();
			rVo.setClickedid(userName);
			rVo.setNum(Integer.valueOf(num));
			rDao.insertCount(rVo);
		}

		CommentDAO cDao = CommentDAO.getInstance(); // 댓글 가져오기
		ArrayList<CommentVO> commentList = cDao.selectCommentByNum(num);

		BoardVO bVo = bDao.selectOneBoardByNum(num);
		request.setAttribute("board", bVo);
		request.setAttribute("commentList", commentList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
