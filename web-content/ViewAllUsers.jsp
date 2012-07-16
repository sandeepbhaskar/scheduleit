<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="UserManager.UsersInfo"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int i = 0;
int noOfUsers = 0;
try
{
	UsersInfo [] listUsers 	= new UsersInfo[10];
	UsersInfo validUser 	= new UsersInfo("", "","","","","","","");
	listUsers				= validUser.getAllUsers();
	noOfUsers				= validUser.countUsers();
	while(i < noOfUsers)
	{
		out.println("Login Id: "+listUsers[i].userLoginId);
		out.println("First Name: "+listUsers[i].userFirstName);
		out.println("Second Name: "+listUsers[i].userSecondName);
		out.println("Email: "+listUsers[i].userEmailId);
		out.println("Category: "+listUsers[i].userCategory);
		out.println("<br><br>");
		i++;		
	}
}
catch(Exception e)
{
	out.println("Error in view all users.jsp "+e);
}
%>
<a href = "index.html">Back to Index</a>
</body>
</html>