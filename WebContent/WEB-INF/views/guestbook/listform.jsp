<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hanains.mysite.dao.GuestbookDao" %>
<%@ page import="com.hanains.mysite.vo.GuestbookVo" %>
<%@ page import="java.util.List" %>
<%
	List<GuestbookVo> list = (List<GuestbookVo>)request.getAttribute("list");
%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/guestbook" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<% 
					for(GuestbookVo vo : list){
				%>
				<ul>
					<li>
						<table>
							<tr>
								<td><%=vo.getNo()%></td>
								<td><%=vo.getName() %></td>
								<td><%=vo.getDate() %></td>
								
								<td><a href="/mysite/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4><%=vo.getMessage()%><br>
								</td>
							</tr>
						</table> <br>
					</li>
				</ul>
				<% 
					}
				%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>