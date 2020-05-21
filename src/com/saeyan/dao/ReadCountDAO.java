package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saeyan.dto.ReadCountVO;

import util.DBManager;

public class ReadCountDAO {
	private static ReadCountDAO single = null;

	private ReadCountDAO() {
	}

	public static ReadCountDAO getInstance() {
		if (single == null) {
			single = new ReadCountDAO();
		}
		return single;
	}

	// 아이디, 글번호 등록
	public void insertCount(ReadCountVO rVo) {
		String sql = "insert into readcount(" + "clickedid, num) " + "values(?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rVo.getClickedid());
			pstmt.setInt(2, rVo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 아이디 체크
	public boolean idChk(String id, int num) {
		boolean result = true;
		String sql = "select * from readcount where num = ? and clickedid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}

}
