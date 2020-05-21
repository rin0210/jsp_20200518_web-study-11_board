<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>게시글 상세보기</h1>
		<table>
			<tr>
				<th>작성자</th>
				<td>${board.name}</td>
				<th>이메일</th>
				<td>${board.email}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${board.writedate}" /></td>
				<th>조회수</th>
				<td>${board.readcount }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">${board.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><pre>${board.content }</pre></td>
			</tr>
			<tr>
				<td colspan=4><hr></td>
			</tr>

			<c:forEach var="comment" items="${commentList }">
				<tr>
					<td>${comment.id }</td>
					<td>${comment.comment }</td>
					<td><fmt:formatDate value="${comment.writedate }" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan=4><hr color=blue></td>
			</tr>

			<tr>
				<form action="BoardServlet?command=board_comment" method="post">
					<td>작성자 : ${sessionScope.userid }</td>
					<td colspan=2>내용 : <input type=text name="commentmemo"
						size=100></td> <input type=hidden name="num"
						value="${board.num}">
					<td><input type=submit value="save"></td>
				</form>
			</tr>
		</table>
		<br> <br> <input type="button" value="게시글 수정"
			onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'update')">
		<input type="button" value="게시글 삭제"
			onclick="open_win('BoardServlet?command=board_check_pass_form&num=${board.num}', 'delete')">
		<input type="button" value="게시글 리스트"
			onclick="location.href='BoardServlet?command=board_list&nowuser=${sessionScope.userid }'">
		<input type="button" value="게시글 등록"
			onclick="location.href='BoardServlet?command=board_write_form'">
		<input type="button" value="답글 등록"
			onclick="location.href='BoardServlet?command=reply_write_form&num=${board.num }&grp=${board.grp }&seq=${board.seq }&lvl=${board.lvl }'">
	</div>
</body>
</html>