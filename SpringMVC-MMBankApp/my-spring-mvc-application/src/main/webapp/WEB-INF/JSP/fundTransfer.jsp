<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	.fundTransfer{
		 position: relative;
              top: 100px;
              left: 220px;
              width: 420px;
              height:150px;
              border-style: solid;
              border-color: black;
              border-radius: 10px; 
	}
</style>
</head>
<body>
<form action="transfer" class="fundTransfer">
	<label style="position: relative; top:8px; left:20px;">Enter Sender's Account Number</label>
	<input type= "number" name="senderAccountNumber" style="position: relative; top:8px; left:30px;">
	<br>
	<br>
	<label style="position: relative; top:8px; left:20px;">Enter Receiver's Account Number</label>
	<input type= "number" name="receiverAccountNumber" style="position: relative; top:8px; left:20px;">
	<br>
	<br>
	<label style="position: relative; top:8px; left:20px;">Enter Amount to Transfer</label>
	<input type= "number" name="amountToTransfer" style="position: relative; top:8px; left:72px;">
	<br>
	<br>
	<input type="submit" value="Submit" style="position: relative; top:-5px; left:250px;">
	<input type="Reset" value="Clear" style="position: relative; top:-5px; left:258px;">
	
</form>	
<div style="position: relative; top:70px; left:240px;">
		<jsp:include page="homeLink.html"></jsp:include>
</div>
</body>
</html>