<?php
	include_once "../classMain.php";
	$restaurantDetail= new restaurantDetail();
	$restaurantDetail->SetData();
	$Session = rand(0,999999);
?>
<script>
	$( "#rSave" ).button().click(function( event ) {
		var rName =$("#rName").val();
		var rInfo =$("#rInfo").val();
		var rBDish =$("#rBDish").val();
		var rDishTut =$("#rDishTut").val();
		$.ajax({
			type:"POST",
			url:"ajax/restaurentAjax.php?rName="+rName+"&rInfo="+rInfo+"&rBDish="+rBDish+"&rDishTut=" +rDishTut,
			beforeSend:function(){
				$("#loading<?php echo $Session; ?>").slideDown();
				$("#loading<?php echo $Session; ?>").html("Updating");
			},
			success:function(msg){
				$("#loading<?php echo $Session; ?>").html(msg);
				$("#loading<?php echo $Session; ?>").slideUp(1500);
			}
		});
	});
</script>
<link href="stylesheet/Restaurant.css" rel="stylesheet" type="text/css" />
<div id="loading<?php echo $Session; ?>" align="center" class ="DialogBox"></div>
<table class="BoxContainer" width="100%" border="0">
  <tr>
    <td width="16%"><div align="right">Restaurant Name </div></td>
    <td width="84%"><div align="left">
		<input id="rName" value ="<?php echo $restaurantDetail->rName;?>"class="InputBox ui-widget ui-state-default ui-corner-all" type="text">
	</div></td>
  </tr>
  <tr>
    <td><div align="right">Restaurant Info </div></td>
    <td><div align="left">
		<textarea class="TextAreaBox" id="rInfo" class="InputBox ui-widget ui-state-default ui-corner-all" type="text"><?php echo $restaurantDetail->rInfo;?></textarea>
	</div></td>
  </tr>
  <tr>
    <td><div align="right">Restaurant Best Dish </div></td>
    <td><div align="left">
		<input id="rBDish" value ="<?php echo $restaurantDetail->rBDish;?>" class="InputBox ui-widget ui-state-default ui-corner-all" type="text">
	</div></td>
  </tr>
  <tr>
    <td valign="top"><div align="right">Dish Tutorial </div></td>
    <td><div align="left">
		<textarea id="rDishTut" class="TextAreaBox ui-corner-all"><?php echo $restaurantDetail->rTut;?></textarea>		
	</div></td>
  </tr>
  <tr>
    <td><div align="right"></div></td>
    <td><div align="left">
		<input id="rSave" value="Save" class="ui-widget ui-state-default ui-corner-all" type="submit">
	</div></td>
  </tr>
</table>
