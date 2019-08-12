<?php
	$sessionID = rand(0,999999);
?>

<link href="stylesheet/Item.css" rel="stylesheet" type="text/css" />
<script>
$(function() {
    $( "#ItemsMaker" ).accordion({collapsible: true,heightStyle: "Container"});
	$( "#gOpenA" ).button().click(function( event ) {
		$.ajax({
			type:"POST",
			url:"ajax/galleryAjax.php?gList2=galleryBox<?php echo $sessionID; ?>",
			beforeSend:function(){
				$("#loading").slideDown();
				$("#loading").html("Loading..");
			},
			success:function(msg){
				$("#loading").slideUp(2000);
				var winW = $(window).width() - $(window).width()/8;
				var winH = $(window).height() - $(window).height()/8;
					$( "#galleryBox<?php echo $sessionID; ?>" ).dialog({
						  modal: true,
						  hide: {
								effect: "fadeOut",
								duration: 1000
							},
						  buttons: {
							Ok: function() {
							  $( this ).dialog( "close" );
							}
						},
						height: winH,
						width: winW
					});
				$( "#galleryBox<?php echo $sessionID; ?>" ).html(msg);
			}
		});
	});
	$("#itemCategoryAdd").button().click(function(event){
		var cName = $("#cName").val();
		var pID = $("#IconID").val();
		var cID = $("#cID").val();
		if(cName !="" && pID!=""){
			if(cID ==""){
				$.ajax({
					type:"POST",
					url:"ajax/itemAjax.php?cName="+cName+"&pID="+pID,
					beforeSend:function(){
						$("#loading").slideDown();
						$("#loading").html("Saving..");
					},
					success:function(msg){
						$("#loading").html(msg);
						$("#loading").slideUp(2000);
						Clear();
						LoadCategoryList();
					}
				});
			}
			else{
				$.ajax({
					type:"POST",
					url:"ajax/itemAjax.php?cID="+cID+"&cName="+cName+"&pID="+pID,
					beforeSend:function(){
						$("#loading").slideDown();
						$("#loading").html("Updating..");
					},
					success:function(msg){
						$("#loading").html(msg);
						$("#loading").slideUp(2000);
						Clear();
						LoadCategoryList();
					}
				});
			}
		}
		else{
			$("#loading").slideDown();
			$("#loading").html("Error Fill form Correctly!");
			$("#loading").slideUp(2000);
		}
	});
	function LoadCategoryList(){
		$.ajax({
			type:"POST",
			url:"ajax/itemAjax.php?iList=iList",
			beforeSend:function(){
				$("#loading").html("Listing..");
				//$("#loading").slideDown();
			},
			success:function(msg){
				$("#ItemCategoryList").html(msg);
				$("#loading").slideUp(2000);
				LoadCategoryInOptions();
			}
		});
	}
	LoadCategoryList();
	$("#itemCategoryClear").button().click(function(event){
		Clear();
	});
	function Clear(){
		$("#cName").val("");
		$("#IconID").val("");
		$("#cID").val("");
	}
	$("#fCategoryBtn").button({icons: {primary: "ui-icon-refresh"},text: false}).click(function(event){LoadCategoryInOptions();});
	function LoadCategoryInOptions(){
		$.ajax({
			type:"POST",
			url:"ajax/itemAjax.php?iListOption=iListOption",
			beforeSend:function(){
				$("#loadingUser").slideDown();
				$("#loadingUser").html("Loading..");
			},
			success:function(msg){
				$("#loadingUser").html("Loaded..");
				$("#loadingUser").slideUp(1500);
				$("#fCategory").html(msg);
			}
		});
	}
	$("#fSave").button().click(function(event){
		var fID = $("#fID").val();
		var fName = $("#fName").val();
		var fPrice = $("#fPrice").val();
		var fDescription = $("#fDescription").val();
		var fGallery = $("#galleryID").val();
		var fCid = $("#fCategory").val();
		
		if(fName !="" && fPrice!="" && fGallery !="" && fCid !=""){
			if(fID ==""){
				$.ajax({
					type:"POST",
					url:"ajax/foodAjax.php?fName="+fName+"&fPrice="+fPrice+"&fDescription="+fDescription+"&galleryID="+fGallery+"&CID="+fCid,
					beforeSend:function(){
						$("#loadingFood").slideDown();
						$("#loadingFood").html("Saving..");
					},
					success:function(msg){
						$("#loadingFood").html(msg);
						$("#loadingFood").slideUp(2000);
						ClearFItem();
						LoadFoodItems();
					}
				});
			}
			else{
				$.ajax({
					type:"POST",
					url:"ajax/foodAjax.php?fID="+fID+"&fName="+fName+"&fPrice="+fPrice+"&fDescription="+fDescription+"&galleryID="+fGallery+"&CID="+fCid,
					beforeSend:function(){
						$("#loadingFood").slideDown();
						$("#loadingFood").html("Saving..");
					},
					success:function(msg){
						$("#loadingFood").html(msg);
						$("#loadingFood").slideUp(2000);
						ClearFItem();
						LoadFoodItems();
					}
				});
			}
		}
		else{
			$("#loadingFood").slideDown();
			$("#loadingFood").html("Fill the form correctly");
			$("#loadingFood").slideUp(2000);
		}
	});
	$("#fClear").button().click(function(event){
		ClearFItem();
	});
	function LoadFoodItems(){
		$.ajax({
			type:"POST",
			url:"ajax/foodAjax.php?fList=fList",
			beforeSend:function(){
				$("#loadingFood").slideDown();
				$("#loadingFood").html("Listing..");
			},
			success:function(msg){
				$("#loadingFood").slideUp(1500);
				$("#FoodContainer").html(msg);
			}
		});
	}
	LoadFoodItems();
	function ClearFItem(){
		$("#fName").val("");
		$("#fDescription").val("");
		$("#fPrice").val("");
		$("#galleryID").val("");
		$("#fID").val("");
	}
	$( "#fGallery" ).button().click(function( event ) {
		$.ajax({
			type:"POST",
			url:"ajax/galleryAjax.php?gList3=galleryBox<?php echo $sessionID; ?>",
			beforeSend:function(){
				$("#loadingFood").slideDown();
				$("#loadingFood").html("Loading..");
			},
			success:function(msg){
				$("#loadingFood").slideUp(2000);
				var winW = $(window).width() - $(window).width()/8;
				var winH = $(window).height() - $(window).height()/8;
					$( "#galleryBox<?php echo $sessionID; ?>" ).dialog({
						  modal: true,
						  hide: {
								effect: "fadeOut",
								duration: 1000
							},
						  buttons: {
							Ok: function() {
							  $( this ).dialog( "close" );
							}
						},
						height: winH,
						width: winW
					});
				$( "#galleryBox<?php echo $sessionID; ?>" ).html(msg);
			}
		});
	});
});
</script>
<div id="galleryBox<?php echo $sessionID; ?>" title="Gallery Viewer" style="width:500px;height:90%;"></div>
<div class="Items">
	<div id="ItemsMaker">
		<h3>Item Category</h3>
		<div>
			  <div class="Container">
			  		<table width="100%">
						<tr>
						  <td width="24%"><div align="right">Category Name:</div></td>
						  <td width="56%">
								<input id="cID" type="hidden"/>
								<input id="cName" class="inputData ui-widget ui-state-default ui-corner-all" type="text"/>
						  </td>
						</tr>
						<tr>
						  <td width="24%"><div align="right">Icon:</div></td>
						  <td width="56%">
								<input id="gOpenA" type="submit" id="itemChooseIcon" style="margin:4px;" value="Choose Icon">
								<input id="IconID" type="text" class="inputData ui-widget ui-state-default ui-corner-all" style="width:50px;top:2px;position:relative;background:rgba(197, 230, 241, 0.78);" id="itemChooseID" style="margin:4px;" value="" Disabled>
						  </td>
						</tr>
						<tr>
							<td></td>
							 <td width="20%">
								<div align="left">
									<input type="submit" id="itemCategoryAdd" style="margin:4px;" value="Save">
									<input type="submit" id="itemCategoryClear" style="margin:4px;" value="Clear">
								</div>
							  </td>
						</tr>
						<tr>
						  <td colspan="2" width="100%"><div style="font-size:14px;" id="loading" align="center"></div></td>
						</tr>
					 </table>
			  </div>
			  <div id="ItemCategoryList" class="Container">
					
			  </div>
			  
		</div>
		<h3>Food Items</h3>
		<div>
			<div class="Container">
			  		<table width="100%">
						<tr>
						  <td width="13%"><div align="right">Item Name</div></td>
						  <td width="36%"><div align="left"><input id="fName" type="text"  class="inputBack ui-widget ui-state-default ui-corner-all"/></div></td>
						  <td rowspan="4"><div align="left" style="padding:5px;">
								<textarea class="textarea" id="fDescription" style="margin:4px;" placeholder="Enter Description Here.."></textarea>
						  </div></td>
						</tr>
						<tr>
						  <td width="13%"><div align="right">Price</div></td>
						  <td width="36%"><div align="left"><input id="fPrice" type="text" class="inputBack ui-widget ui-state-default ui-corner-all"/></div></td>
						</tr>
						<tr>
						  <td width="13%"><div align="right">Category</div></td>
						  <td width="36%"><div align="left">
								<select style="width:70%;" id="fCategory" class="inputBack ui-widget ui-state-default ui-corner-all"></select>
								<button style="top:-2px;position:relative;" id ="fCategoryBtn">Button with icon only</button>							
						  </div></td>
						 </tr>
						<tr>
						  <td width="13%"><div align="right">Gallery</div></td>
						  <td width="36%"><div align="left">
								<input type="submit" id="fGallery" style="margin:4px;" value="Choose Gallery">
								<input id="galleryID" type="text" class="inputData ui-widget ui-state-default ui-corner-all" style="width:50px;top:2px;position:relative;background:rgba(197, 230, 241, 0.78);" id="itemChooseID" style="margin:4px;" value="" Disabled>
							</div></td>
						</tr>
						<tr>
						  <td><input id="fID" type="hidden"/></td>
						  <td><div align="left">
								<input type="submit" id="fSave" style="margin:4px;" value="Save">
								<input type="submit" id="fClear" style="margin:4px;" value="Clear">
							</div>
						  </td>
						</tr>
						<tr>
						  <td colspan="4" width="100%"><div style="font-size:14px;" id="loadingFood" align="center"></div></td>
						</tr>
					  </table>
			  </div>
			  <div id="FoodContainer" class="FoodContainer">
					
			  </div>
		</div>
  </div>
</div>