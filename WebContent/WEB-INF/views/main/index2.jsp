<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/main.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<!-- <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include> -->
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="/mysite/assets/images/picture.jpg">
					<h2>안녕하세요. 문준성의 mysite에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는 웹 프로그램밍 실습과제 사이트입니다.<br> <br>
						<br> <a href="/mysite/guestbook?a=listform">방명록</a>에 글 남기기<br>
					</p>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
			<c:param name = "menu" value="main"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
		<!-- <jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include> -->
		<!-- <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include> -->
	</div>
</body>
</html>