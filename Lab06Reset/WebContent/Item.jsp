<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ page import="model.Item" %>



<%
	if (request.getParameter("itemCode") != null) 
	{ 
 		Item itemObj = new Item();
 		itemObj.connect();
 //For testing the connect method
 
	}
	
	//Insert item------------------------------
	if(request.getParameter("itemCode") != null){
		
		Item itemObj = new Item();
		
		String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),
				request.getParameter("itemName"),
				request.getParameter("itemPrice"),
				request.getParameter("itemDesc"));
		
		session.setAttribute("statusMsg",stsMsg);
	}
	//Remove item------------------------------
	if(request.getParameter("itemId") != null){
		
		String  id = request.getParameter("itemId");
		
		Item itemObj = new Item();
		
/*		String[] item = itemObj.readItems(id);
		
		String code = item[0];
		String name = item[1];
		String price = item[2];
		String desc = item[3];
		
		String stsMsg = itemObj.deleteItem(id);
		
		session.setAttribute("statusMsg",stsMsg);
		session.setAttribute("code",code);
		session.setAttribute("name",name);
		session.setAttribute("price",price);
		session.setAttribute("desc",desc);
		*/
	}	
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>




<h1>Items Management</h1>

<br><br>

<form method="post" action="Item.jsp">
 Item code: <input name="itemCode" type="text" <%if(request.getParameter("btnUpdate") != null){%>
		value='<%= session.getAttribute("code")  %>'
	<% }%>><br>

	Item name: <input name="itemName" type="text" <%if(request.getParameter("btnUpdate") != null){%>
		value="<%= session.getAttribute("name") %>"
	<% }%>><br>

	Item price: <input name="itemPrice" type="text" <%if(request.getParameter("btnUpdate") != null){%>
		value="<%= session.getAttribute("price") %>"
	<% }%>><br>

	Item description: <input name="itemDesc" type="text" <%if(request.getParameter("btnUpdate") != null){%>
		value="<%= session.getAttribute("desc") %>"
	<% }%>><br>

	<input name="btnSubmit" type="submit" value="Save">
	</form>

	<%
		//out.print(item[0]);
		out.print(session.getAttribute("statusMsg"));
	
	%>
	<br>
	<%
	
		Item itemObj = new Item();
		out.print(itemObj.readItems());
	
	%>
	
	
	

</body>
</html>