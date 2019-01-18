<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.searchBy{
				border-radius:10px;
				width:320px;
				top:100px;
				height:100px;
				position: relative;
				left: 230px;  
				border-style: solid;
                border-color: black;
              	border-radius: 10px; 
	}
	a{
		text-decoration: none;
	}
</style>
</head>
<body>
<div class="searchBy">
	<ul>
		<li><a href="searchByAccountId">Search Account By Account Id</a></li>
		<li><a href="searchByName">Search Account By Name</a></li>
		<li><a href="searchByBalanceRange">Search Account By Balance Range</a></li>
	</ul>
</div>	
</body>
</html>