package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardList.jsp";
		BoardDAO bDao = BoardDAO.getInstance();
		List<BoardVO> boardList = bDao.selectAllBoards();
		if (boardList != null) {

			String a = "┖→&nbsp;";
			String b = "&ensp;";
			String c = "";

			for (BoardVO bv : boardList) {
				if (bv.getLvl() >= 1) {
					for (int i = 0; i < bv.getLvl(); i++) {
						c += b;
					}
					c = c + a;
					bv.setTitle(c + bv.getTitle());
					c = "";
				} 
			}

			request.setAttribute("boardList", boardList);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
