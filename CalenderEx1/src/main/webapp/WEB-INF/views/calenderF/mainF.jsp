<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<c:forEach var="vo" items="${list}">
			<a href="cal_user.do?command=calender&id=${vo.id}&&team_name=${vo.team_name}"> ${vo.id} </a> / 
			<a href="cal_team.do?command=calender&id=${vo.id}&&team_name=${vo.team_name}"> ${vo.team_name} </a>
		<br>
		</c:forEach>
	</body>
</html>