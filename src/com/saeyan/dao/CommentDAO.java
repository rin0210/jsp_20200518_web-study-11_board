package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.saeyan.dto.CommentVO;

import util.DBManager;

public class CommentDAO {
	private static CommentDAO single = null;

	private CommentDAO() {
	}

	public static CommentDAO getInstance() {
		if (single == null) {
			single = new CommentDAO();
		}
		return single;
	}

	// 댓글 등록
	public void insertComment(CommentVO cVo) {
		String sql = "insert into commentmemo(" + "num, id, content) " + "values(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cVo.getNum());
			pstmt.setString(2, cVo.getId());
			pstmt.setString(3, cVo.getComment());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 댓글 가져오기
	public ArrayList<CommentVO> selectCommentByNum(String num) {
		String sql = "select * from commentmemo where num = ? order by writedate";
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
		CommentVO cVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVo = new CommentVO();
				cVo.setNum(rs.getInt("num"));
				cVo.setId(rs.getString("id"));
				cVo.setComment(rs.getString("content"));
				cVo.setWritedate(rs.getTimestamp("writedate"));
				commentList.add(cVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return commentList;
	}

}
