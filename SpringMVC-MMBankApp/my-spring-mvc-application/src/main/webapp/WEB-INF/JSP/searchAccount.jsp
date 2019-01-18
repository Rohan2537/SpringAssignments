<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
		.searchAccount{
				 position: relative;
              top: 100px;
              left: 200px;
              width: 350px;
              height:80px;
              border-style: solid;
              border-color: black;
              border-radius: 10px; 
		}
</style>
</head>
<body>
<form action="searchById" class="searchAccount">	
	<label style="position: relative; left: 10px; top:5px;">Enter Account Number</label>
	<input type= "number" name="accountNumber"style="position: relative; left: 10px; top:5px;">
	<br>
	<input type="submit" value="Submit" style="position: relative; left: 160px; top:12px;">
	<input type="Reset" value="Clear" style="position: relative; left: 180px; top:12px;">
	<div style="position: relative; top:-8px; left:60px;">
			<jsp:include page="homeLink.html"></jsp:include>
	</div>
</form>	
</body>
</html>