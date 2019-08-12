<?php
	if(isset($_REQUEST['res_name']) 
		&& isset($_REQUEST['res_info']) 
		&& isset($_REQUEST['res_bestdish'])
		&& isset($_REQUEST['res_tutorial'])){
		
		include 'classMain.php';
		
		$restaurantDetail = new restaurantDetail();
		$restaurantDetail->Insert($_REQUEST['res_name'],$_REQUEST['res_info'],$_REQUEST['res_bestdish'],$_REQUEST['res_tutorial']);
	} 
	else if(isset($_REQUEST['SN'])){
		include 'classMain.php';
		$restaurantDetail = new restaurantDetail();
		$restaurantDetail->Delete($_REQUEST['SN']);
	}
	else if(isset($_REQUEST['rSN'])
		&&isset($_REQUEST['name_res'])
		&& isset($_REQUEST['info_res'])
		&& isset($_REQUEST['bestdish_res']) 
		&& isset($_REQUEST['tutorial_res'])){
		include 'classMain.php';
		$restaurantDetail = new restaurantDetail();
		$restaurantDetail->Update($_REQUEST['rSN'],$_REQUEST['name_res'],$_REQUEST['info_res'],$_REQUEST['bestdish_res'],$_REQUEST['tutorial_res']);
	}
	else{
		include 'classMain.php';
		$restaurantDetail = new restaurantDetail();
		$restaurantDetail->Select();
	}

?>