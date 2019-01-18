<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	.withdrawAmount{
			  position: relative;
              top: 100px;
              left: 220px;
              width: 400px;
              height:130px;
              border-style: solid;
              border-color: black;
              border-radius: 10px; 
	}
</style>
</head>
<body>
	<form action="withdrawAmount" class="withdrawAmount">
						<label style="position: relative; top:8px; left:30px;">Enter Account Number</label>
						<input type= "number" name="accountNumber" style="position: relative; top:8px; left:50px;">
						<br>
						<br>
						<label style="position: relative; top:8px; left:30px;">Enter Amount to Withdraw</label>
						<input type= "number" name="amountToWithdraw" style="position: relative; top:8px; left:30px;">
						<br>
						<input type="submit" value="Submit" style="position: relative; top:20px; left:210px;">
						<input type="Reset" value="Clear" style="position: relative; top:20px; left:220px;">		
		</form>
		<div style="position: relative; top:50px; left:255px;">
		<jsp:include page="homeLink.html"></jsp:include>
		</div>
</body>
</html>