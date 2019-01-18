<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="sortAll">
						<label>Sort Accounts By</label>
						<label>1.Account Number</label>
						<label>2.Account Holder Name</label>
						<label>3.Account Balance</label>
						<input type= "choice" name="Choice">
						<input type="submit" value="Submit">
						<input type="Reset" value="Clear">		
		</form>
		<div>
		<jsp:include page="homeLink.html"></jsp:include>
		</div>
</body>
</html>