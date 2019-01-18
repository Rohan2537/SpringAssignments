<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style >
		.addNewAcc{
			  position: relative;
              top: 100px;
              left: 200px;
              width: 350px;
              height:200px;
              border-style: solid;
              border-color: black;
              border-radius: 10px;
		}
	.createAccount{
	position:relative;
		top: 20px;
		left:20px;
	}

</style>
</head>
<body>
	<div class="addNewAcc">
		<form action="createAccount" class="createAccount">
						<label class="name" >Enter your Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<input type= "text" name="accHolderName">
						<br>
						<br>
						<label>Enter account Balance</label>
						<input type= "number" name="accountBalance">
						<br>
						<br>
						<label>Enter salaried?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
						<input type="radio" name="rdSalary" value="Yes">Yes
						<input type="radio" name="rdSalary" value="No">No
						<br>
						<br>
						<input type="submit" value="Submit" style="position: relative; left: 70px;">
						<input type="Reset" value="Clear"style="position: relative; left: 90px;">
				<div style="position: relative; top:8px; left:120px;">
						<jsp:include page="homeLink.html"></jsp:include>
				</div>
		</form>
	</div>
</body>
</html>