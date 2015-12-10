<%@page import="com.hanains.mysite.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
	List<BoardVo> list = (List<BoardVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
			
				<%=list.size() %>
					<%
						for(BoardVo vo : list){
					%>		
					<tr>
						<td><%=vo.getNo() %></td>
						<td><a href="/mysite/board?a=view"><%=vo.getTitle() %></a></td>
						<td><%=vo.getName() %></td>
						<td><%=vo.getMember_no() %></td>
						<td><%=vo.getDate() %></td>
						<td><a href="" class="del">삭제</a></td>
					</tr>	
					<%
						}
					%>
				
					<tr>
						<td>1</td>
						<td><a href="/mysite/board?a=view">test</a></td>
						<td>test</td>
						<td>1 </td>
						<td>2015</td>
						<td><a href="" class="del">삭제</a></td>
					</tr>						
				</table>
				<div class="pager">
					<ul>
						<li class="pg-prev"><a href="#">◀ 이전</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li class="disable">4</li>
						<li class="disable">5</li>
						<li class="pg-next"><a href="#">다음 ▶</a></li>
					</ul>	
				</div>
				<div class="bottom">
					<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>