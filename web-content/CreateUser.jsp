<%@ page import="com.db4o.*"%>
<%@ page import="UserManager.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	boolean retValue = false;
	String userLoginId 		= request.getParameter("userLoginId");
	String userFirstName 	= request.getParameter("userFirstName");
	String userSecondName 	= request.getParameter("userSecondName");
	String userEmailId 		= request.getParameter("userEmailId");
	String userPassword 	= request.getParameter("userPassword");
	String userCategory 	= request.getParameter("userCategory");
	String userDefaultOffice = request.getParameter("userDefaultOffice");
	String userAccessLevel	= request.getParameter("userAccessLevel");
	
	UserManager.UsersInfo newUser = new UserManager.UsersInfo(userLoginId,userFirstName,userSecondName,userEmailId,userPassword,userCategory,userDefaultOffice,userAccessLevel);
	retValue = newUser.createUser(userLoginId,userFirstName,userSecondName,userEmailId,userPassword,userCategory,userDefaultOffice,userAccessLevel);
	if(retValue == true)
		response.sendRedirect("error1.html");
	else
		response.sendRedirect("error.html");

%>
</body>
</html>