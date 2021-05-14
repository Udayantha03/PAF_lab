$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
	 	$("#alertSuccess").hide();
	 }
	 	$("#alertError").hide();
	});
	// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
		{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		// Form validation-------------------
		var status = validateFundForm();
		if (status != true)
		 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
		 }
		// If valid------------------------
		var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
		 $.ajax(
		 {
		 url : "FundAPI",
		 type : type,
		 data : $("#fundForm").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onFundSaveComplete(response.responseText, status);
		 }
		 });
	
		function onFundSaveComplete(response, status)
	{
		if (status == "success")
		 {
		 	var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully saved.");
			 $("#alertSuccess").show();
			 $("#divFundsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
			 $("#alertError").text("Error while saving.");
			 $("#alertError").show();
		 } else
		 {
			 $("#alertError").text("Unknown error while saving..");
			 $("#alertError").show();
		 } 
	
	}
	
		});

	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	 $("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val());
	 $("#fundcode").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#fundType").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#amount").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#date").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#status").val($(this).closest("tr").find('td:eq(4)').text());
	});
// CLIENT-MODEL================================================================

	$(document).on("click", ".btnRemove", function(event)
	{
	 $.ajax(
	 {
	 url : "FundAPI",
	 type : "DELETE",
	 data : "fundId=" + $(this).data("fundId"),
	 dataType : "text",
	 complete : function(response, status)
	 {
	 onItemDeleteComplete(response.responseText, status);
	 }
	 });

	function onFundDeleteComplete(response, status)
	{
		if (status == "success")
		 {
		 	var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#divFundsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
		 } else if (status == "error")
		 {
			 $("#alertError").text("Error while deleting.");
			 $("#alertError").show();
		 } else
		 {
			 $("#alertError").text("Unknown error while deleting..");
			 $("#alertError").show();
		 }
	}
	});
	function validateFundForm()
	{
		// CODE
		if ($("#fundCode").val().trim() == "")
		 {
		 	return "Insert fund Code.";
		 }
		// type
		if ($("#fundType").val().trim() == "")
		 {
		 	return "Insert fundType.";
		 } 
	
		// status-------------------------------
		if ($("#status").val().trim() == "")
		 {
		 	return "Insert status.";
		 }
		if ($("#date").val().trim() == "")
		 {
		 	return "Insert Time.";
		 }
		// is numerical value
		var amount = $("#amount").val().trim();
		if (!$.isNumeric(amount))
		 {
		 	return "Insert an Amount.";
		 }
		
		
		
		return true;

	
	
	
	
}
