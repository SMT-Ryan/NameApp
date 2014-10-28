<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--
	-	Title: result.jsp
	-	Description: This page is the dynamically generated response  
	-	Copyright: Copyright (c) 2014
	-	Company: Silicon Mountain Technologies
	-	@author: Ryan J Riker
	-	@Version 1.1
	-	@since 10/20/2014
	-	Last update: Never 
	-->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>My name is ${name}</h1>
	<h2>The count is: ${TrafficCount}</h2>

	<br />

	<p>the length of the request is ${reqLength} bytes</p>
	<br/>

</body>
</html>


