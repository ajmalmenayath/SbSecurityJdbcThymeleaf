$(document)
		.ready(
				function() {

					// SUBMIT FORM
					$("#QUForm").submit(function(event) {
						// Prevent the form from submitting via the browser.
						event.preventDefault();
						ajaxPost();
					});

					function ajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							cid : $('#cid').val(),	
							cname : $("#cname").val(),
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
									url : "/GeneretedUpdate",
									data : JSON.stringify(formData),
									dataType : 'json',
									success : function(data) {
										
											 $('#Qdetails').empty ();
											 $('#Qdetails').append(
													 "<table>" +
													 													
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
											 $('#QUForm').hide();
											 $('#qupdate').show();
											 $('#notes').show();
											 $('#hnote').show();
											 $('#qtbutton').hide();
											
											 /*
											 $( '#cid' ).val(data.cid);
											 $( '#cid' ).val(data.cid);
											 
											 */

											
																										 
										
										
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
	    $('#QUForm').show();
	    $('#qtbutton').show();
	    
	  });
	 
	});
$( document ).ready(function() {
 
	$('#shownotes').on("click",'button',function(e){
	    var idClicked = e.target.id;
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/delete_updatedQtn",
	        data : {
	      	   "id" : idClicked,
	      	   "cid" : $('#cid').val(),
	      	    },
	        success: function(result){
	        	
	        	 $('#'+result+'tr').remove();
	        	
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
});		

$( document ).ready(function() {
	 
	$('#listcat').on("click",'button',function(e){
		var id = $(this).attr("id");
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/listitems",
	        data : {
	      	   "subid" : id,
	      	    "cid" : $('#cid').val(),	
	      	    },
	        success: function(result){
	        	
	        	if(result.itemid==0) { 
	        		
	        		 $('#footid').show();
	        	    	
		        	 $('#itemtable tbody').append('<tr id="'+result.sub_id+'" style="width=20%"><td><input type="text" id="'+result.sub_id+'item" style="width: 100%;" value="'+result.item_name+'" readonly />'+
		        			 '</td><td><input type="text"  id="'+result.sub_id+'price" style="width: 100%; text-align:right;" value="'+result.price+'"/>'+
		        			 '</td><td>*</td><td><input type="text"  id="'+result.sub_id+'count" style="width: 100%; text-align:right;" value="'+result.count+'"/>'+
		        			 '</td><td>=</td><td><input type="text"  id="'+result.sub_id+'total" style="width: 100%; text-align:right;" value="'+result.total+'" readonly />'+
		        			 '</td><td><button type="button" id="'+result.sub_id+'btn">delete</button></td></tr>');	  
		        	 
		        	 $('#footsum').val(result.sum);
		        	 $("#itemtable tr:last td:first input").focus();
	        	 }
	        	else {
	        		var price="#"+result.sub_id+"price";
	        		var count="#"+result.sub_id+"count";
	        		var total="#"+result.sub_id+"total";
	        		var item="#"+result.sub_id+"item";
	        		
	        		$(item).focus();
					
	        		$(price).val(result.price);
	        		$(count).val(result.count);
	        		$(total).val(result.total);
	        		$('#footsum').val(result.sum);
	        		
				}
	        	 	        	      	   
	      	 
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});

$( document ).ready(function() {
	 
	$('#tablediv').on("click",'button',function(e){
	    var idClicked = e.target.id;
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/delete_item",
	        data : {
	      	   "btid" : idClicked,
	      	   "cid" : $('#cid').val(),	
	      	    },
	        success: function(result){
	        	if(result.sub_id!=0){
	        		$('#'+result.sub_id).remove();
	        		$('#footsum').val(result.sum);
	        		if(result.sum==0){
	        			$('#footid').hide();
	        		}
	        	}
	        	else{
	        		alert("Delete Failed");
	        	}
	        		
	        
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});
$( document ).ready(function() {
	 
	$('#btcontainer').on("click",'button',function(e){
	    var idClicked = e.target.id;
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/listcat_name",
	        data : {
	      	   "cat_name" : idClicked,
	      	    },
	        success: function(result){
	        		        	
	        	 $('#listcat').empty ();
	        	
	        	 
	        	 $.each(result, function( index, value ) {
	        		 $('#listcat').append('<button type="button" id="'+value.sub_id+'">'+
	        				 '<h4>'+value.sub_name+'</h4>'+
	        				'<img src="/image/logo.jpeg" alt="select" title="select" width="100" height="50"/>'+
	        				'<h4 style="white-space: nowrap;">price:</h4>'+
	        				'<h4>'+value.price+'</h4></button>');
	                   
	              });  
				
					        		        	      	   
	      	 
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});


$( document ).ready(function() {
	 
	$('#manualAdd').on("click",'button',function(e){
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/manualItemAdd",
	        data : {
	      	   "item_id" : $('#mtid').val(),
	      	   "cid" : $('#cid').val(),
	      	   "item_name" : $('#mname').val(),
	      	   "price" : $('#mprice').val(),
	      	   "count" : $('#mquantity').val(),
	      	    },
	        success: function(result){
	        	if(result.itemid==0) { 
	        		$('#footid').show();
	        		
		        	$('#itemtable tbody').append('<tr id="'+result.sub_id+'" style="width=20%"><td><input type="text" id="'+result.sub_id+'item" style="width: 100%;" value="'+result.item_name+'" readonly />'+
		        			 '</td><td><input type="text"  id="'+result.sub_id+'price" style="width: 100%; text-align:right;" value="'+result.price+'"/>'+
		        			 '</td><td>*</td><td><input type="text"  id="'+result.sub_id+'count" style="width: 100%; text-align:right;" value="'+result.count+'"/>'+
		        			 '</td><td>=</td><td><input type="text"  id="'+result.sub_id+'total" style="width: 100%; text-align:right;" value="'+result.total+'" readonly />'+
		        			 '</td><td><button type="button" id="'+result.sub_id+'btn">delete</button></td></tr>');
		        	 $('#footsum').val(result.sum);
		        	 $("#itemtable tr:last td:first input").focus();
		        	
		        	$('#manualAdd input').val('');
	        	}
	        	else {
	        		var price="#"+result.sub_id+"price";
	        		var count="#"+result.sub_id+"count";
	        		var total="#"+result.sub_id+"total";
	        		var item="#"+result.sub_id+"item";
	        		
	        		$('#manualAdd input').val('');
	        		
	        		$(item).focus();
					
	        		$(price).val(result.price);
	        		$(count).val(result.count);
	        		$(total).val(result.total);
	        		$('#footsum').val(result.sum);
	        		
	        		
	        	}
	        		
	        
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});


$(document).ready(function(){

    // Initialize 
    $( "#mname" ).autocomplete({
       source: function( request, response ) {
         // Fetch data
         $.ajax({
           url: "/autocmplt_mname",
           type: 'GET',
           dataType: "json",
           data: {
             "term": request.term
           },
           success: function( data ) {
           	
           	 response(data.map(function(value){
                    console.log(value);
                    return {
                    	value: value.sub_name,
                    	sub_id: value.sub_id,
                    	price: value.price,
                    	
                    };
                }));

           }
         });
       },
       select: function (event, ui) {
         // Set selection
         $('#mname').val(ui.item.value); // display the selected text
         $('#mtid').val(ui.item.sub_id);
         $('#mprice').val(ui.item.price);
         $('#mquantity').val("1");
         //$('#iso').val(ui.item.description); // save selected id to input
         return false;
       }
     });

   });





$( document ).ready(function() {
	 
	$('#tablediv').on("change",'input[type=text]',function(e){
	   
	    var trid = $(this).closest('tr').attr('id');
	    var price=$('#'+trid+'price').val();
	    var count=$('#'+trid+'count').val();
	    var total=$('#'+trid+'total').val();
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/update_item",
	        data : {
	        		"trid" : trid,
	        		"price" : price,
	        		"count" : count,
	        		"total" : total,
	        		"cid" : $('#cid').val(),
	        		
	        		
	      	    },
	        success: function(result){
	        	
	        	var price1="#"+result.sub_id+"price";
        		var count1="#"+result.sub_id+"count";
        		var total1="#"+result.sub_id+"total";
				
        		$(price1).val(result.price);
        		$(count1).val(result.count);
        		$(total1).val(result.total);
        		$('#footsum').val(result.sum);
        		
        		
	        	
	        		
	        
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});


$( document ).ready(function() {
	 
	$('#supply_dt').change(function(){
	   
	    var cid = $('#cid').val();
	    var supply_dt=$('#supply_dt').val();
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/Insertsupply_dt",
	        data : {
	        	
	        		"cid" : cid,
	        		"supply_dt" : supply_dt,
	        		
	      	    },
	        success: function(result){
	        	
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});


$( document ).ready(function() {
	 
	$('#lpo').change(function(){
	   
	    var cid = $('#cid').val();
	    var lpo=$('#lpo').val();
	   
	    event.preventDefault();
	    $.ajax({
	        type : "GET",
	        url : "/Insertlpo",
	        data : {
	        	
	        		"cid" : cid,
	        		"lpo" : lpo,
	        		
	      	    },
	        success: function(result){
	        	
	        },
	        error : function(e) {
	  			alert("Error!")
	  			console.log("ERROR: ", e);
	        }
	      });  
	});
		
	 
	});


$( document ).ready(function() {

	$('#Taxpdfbutton').click(function(e) {

		var cid = $('#cid').val();
		var sUrl='/TaxInvoicepdfreport/'+cid;
		window.open(sUrl);
		$('#finish').show();
		
	    
		});
	});


$( document ).ready(function() {

	$('#pdfbutton').click(function(e) {

		var cid = $('#cid').val();
		var sUrl='/pdfreport/'+cid;
		window.open(sUrl);
		$('#finish').show();
		
	    
		});
	});

$( document ).ready(function() {

	$('#DNpdfbutton').click(function(e) {

		var cid = $('#cid').val();
		var sUrl='/DNpdfreport/'+cid;
		window.open(sUrl);
		$('#finish').show();
		
	    
		});
	});

	$( document ).ready(function() {

		$('#finish').click(function(e) {
			
			    var ask = window.confirm("Are you sure finish the quotation?");
			    if (ask) {
			       // window.alert("This Quotation was successfully Finished");

					window.location.replace("/admin");


			    }

		    
		});
	});


	$( document ).ready(function() {

		$('#update_all').click(function(e) {
			
			
			    var ask = window.confirm("Are you sure to Update this Quotation!!!!!?");
			    if (ask) {
			       // window.alert("This Quotation was successfully Finished");
				    history.pushState(null, null, location.href);
				    history.back();
					history.forward();
					window.onpopstate = function () { history.go(1); };
			    	
			    	$('#allPage').show();
			    	$('#Start').hide();
			    	$('#update_all').hide();
			    	$('#discart_btn').show();
			    	
			    }

		    
		});
	});


	$( document ).ready(function() {

		$('#discart_btn').click(function(e) {
			
			
			    var ask = window.confirm("Are you sure to Discard this Quotation!!!!!?");
			    if (ask) {
			       // window.alert("This Quotation was successfully Finished");
			    	var cid = $('#cid').val();
			    	var sUrl='/discard/'+cid;
					window.location.replace(sUrl);


			    }

		    
		});
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
							url : "/addUpdatednote",
							data : JSON.stringify(formData),
							dataType : 'json',
							success : function(data) {
								 $('#notes').show();
								// $('#qtbutton').hide();
								 $( '#cid' ).val(data.cid);	
								 $( "#note" ).val("");
								 $('#additems').show();
								 $('#notetable tbody').append('<tr id="'+data.nid+'tr"><td style="width:65% ;height:25px;"><h3 id="'+data.nid+'h"> *'+data.note+'</h3></td><td style="width:35%; height:25px;"><button id='+data.nid+' type="button">delete</button></td></tr>');
								
								 
							},
							error : function(e) {
								alert("Error!") 
								console.log("ERROR: ", e);
							}
						});

			}

		});	




