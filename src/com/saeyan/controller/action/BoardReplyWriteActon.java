package com.saeyan.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardReplyWriteActon implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grp = request.getParameter("grp");
		String seq = request.getParameter("seq");
		String lvl = request.getParameter("lvl");
		int mySeq = Integer.valueOf(seq);
		int myLvl = Integer.valueOf(lvl);

		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = new BoardVO();

		ArrayList<BoardVO> list = bDao.selectOneGroupBoards(grp);
		if (myLvl == 0) { // 원글에 대한 답글이면
			for (BoardVO bv : list) {
				mySeq++;
			}

		} 
		else {
			mySeq = mySeq + 1;

			for (BoardVO bv : list) {
				if (mySeq == bv.getSeq() && (myLvl + 1) == bv.getLvl()) {
					mySeq++;
					if ((myLvl + 1) > bv.getLvl()) {
						break;
					}
				}
			}
		}

		bVo.setName(request.getParameter("name"));
		bVo.setPass(request.getParameter("pass"));
		bVo.setEmail(request.getParameter("email"));
		bVo.setTitle(request.getParameter("title"));
		bVo.setContent(request.getParameter("content"));
		bVo.setGrp(Integer.valueOf(grp));
		bVo.setSeq(mySeq);
		bVo.setLvl(myLvl + 1);

		bDao.updateReplySeq(bVo);

		new BoardListAction().execute(request, response);
	}
}
