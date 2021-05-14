<%@page import="com.Fund"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fund Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/fund.js"></script>

</head>
<body>




	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Fund Management</h1>
				<form id="formFund" name="formFund">
					Fund Code : <input id="fundCode" name="fundCode" type="text" class="form-control form-control-sm"> <br> 
					Fund Type : <input id="fundType" name="fundType" type="text" class="form-control form-control-sm"> <br> 
					Amount : <input	id="amount" name="amount" type="text" class="form-control form-control-sm"> <br> 
					Date : <input id="date" name="date" type="text" class="form-control form-control-sm"> <br> 
					Status : <input id="status" name="status" type="text" class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
				</form>
				
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divFundsGrid">
					<%
					Fund fundObj = new Fund();
					out.print(fundObj.readFunds());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>