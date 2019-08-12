<?php
	include_once("../../classMain.php");	
	if(isset($_GET['rName']) && isset($_GET['rInfo']) 
	&& isset($_GET['rBDish']) && isset($_GET['rDishTut'])){
		$restaurantDetail= new restaurantDetail();
		$restaurantDetail->Update($_GET['rName'],$_GET['rInfo'],$_GET['rBDish'],$_GET['rDishTut']);
	}
	else{
		echo "Nothing return";
	}
	
?>