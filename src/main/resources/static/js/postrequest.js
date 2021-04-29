$(document)
		.ready(
				function() {

					// SUBMIT FORM
					$("#QForm").submit(function(event) {
						// Prevent the form from submitting via the browser.
						$('#discart_btn').show();
						
				        history.pushState(null, null, location.href);
				        history.back();
				        history.forward();
				        window.onpopstate = function () { history.go(1); };
				
						event.preventDefault();
						ajaxPost();
					});

					function ajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							cid : $('#cid').val(),	
							cname : $("#cname").val(),
							trnno: $('#trnno').val(),
							emirates: $('#emirates').val(),
							validity : $("#validity").val(),
							delivery : $("#delivery").val(),
							warranty : $("#warranty").val(),
							payment : $("#payment").val(),
							cam_type : $("#cam_type").val()
						}

						// DO POST
						$
								.ajax({
									type : "POST",
									contentType : "application/json",
									url : "/addqtdetails",
									data : JSON.stringify(formData),
									dataType : 'json',
									success : function(data) {
										
										if(data.cname==null ){
											alert("Customer name not null...");
										}
										else{
											
											 $('#Qdetails').empty ();
											 $('#Qdetails').append(
													 "<table>" +
													  " <tr>" +
													  "    <td>" +
													  "    Company Name"+
													  "		 </td><td>:</td>" +
													  "			<td>"+data.cname+
													  "</td></tr>"+
													  
													  " <tr>" +
													  "    <td>" +
													  "    TRN NO"+
													  "		 </td><td>:</td>" +
													  "			<td>"+data.trnno+
													  "</td></tr>"+
													  
													  " <tr>" +
													  "    <td>" +
													  "    EMIRATES"+
													  "		 </td><td>:</td>" +
													  "			<td>"+data.emirates+
													  "</td></tr>"+
													
													  " <tr>" +
													  "    <td>" +
													  "    Validity"+
													  "		 </td> <td>:</td>" +
													  "			<td>The Offer is Valid for a Period of "+data.validity+
													  "</td></tr>"+

													  " <tr>" +
													  "    <td>" +
													  "    DELIVERY"+
													  "		 </td><td>:</td>" +
													  "			<td> Delivery "+data.delivery+" from receipt of LPO ."+
													  "</td></tr>"+
													  
													  " <tr>" +
													  "    <td>" +
													  "    WARRANTY"+
													  "		 </td> <td>:</td>" +
													  "			<td>"+data.warranty+" warranty."+
													  "</td></tr>"+

													  " <tr>" +
													  "    <td>" +
													  "    PAYMENT "+
													  "		 </td><td>:</td>" +
													  "			<td> Payment with in "+data.payment+
													  "</td></tr>"+
													  " <tr>" +
													  "    <td>" +
													  "    cam_type"+
													  "		 </td> <td>:</td>" +
													  "			<td>"+data.cam_type+"."+
													  "</td></tr>"
													  
													  );
											 $('#Qdetails').show();
											 $('#QForm').hide();
											 $('#notes').show();
											 $('#qupdate').show();
											 $('#hnote').show();
											 $('#qtbutton').hide();
											 $( '#cid' ).val(data.cid);
											 $( '#cid' ).val(data.cid);
											 
											 

											
										}
											
											
																				 
										
										
									},
									error : function(e) {
										alert("Error!")
										console.log("ERROR: ", e);
									}
								});

					}

				});
				
				
				
				
				
				
$(document)
.ready(
		function() {

			// SUBMIT FORM
			$("#noteForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					cid : $('#cid').val(),	
					note : $("#note").val(),
					
				}

				// DO POST
				$
						.ajax({
							type : "POST",
							contentType : "application/json",
							url : "/addnote",
							data : JSON.stringify(formData),
							dataType : 'json',
							success : function(data) {
								 $('#notes').show();
								 $('#qtbutton').hide();
								 $( '#cid' ).val(data.cid);	
								 $( "#note" ).val("");
								 $('#shownotes').show();
								 $('#additems').show();
								 $('#shownotes').append('<table><tr id="'+data.nid+'tr"><td style="width:65% ;height:25px;"><h3 id="'+data.nid+'h"> *'+data.note+'</h3></td><td style="width:35%; height:25px;"><button id='+data.nid+' type="button">delete</button></td></tr></table>');
								
								 
							},
							error : function(e) {
								alert("Error!") 
								console.log("ERROR: ", e);
							}
						});

			}

		});	






$(document).ready(function(){
	  $('#qtupdate').click(function(){
	    $('#qupdate').hide();
	    $('#Qdetails').hide();  
	    $('#QForm').show();
	    $('#qtbutton').show();
	    
	  });
	 
});




$(document).ready(function(){
	
	$('#trnno').on('change', function() {
		var value = Number(this.value);
		 if (Math.floor(value) != value) {
			    // value is an integer, do something based on that
				  alert("TRN NO Must be a number");

				 
			  }
		 if(((this.value).length)!=15 && this.value!=null)
		 {
			 alert("TRN NO Must be 15 digits");
		 }
		  
		});
	
	
});

	