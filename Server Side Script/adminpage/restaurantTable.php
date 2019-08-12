<?php 
if(isset($_REQUEST['tableNO'])){
		
		include 'classMain.php';
		
		$restaurantTable = new restaurantTable();
		$restaurantTable->Insert($_REQUEST['tableNO']);
	} 
	else if(isset($_REQUEST['SN'])){
		include 'classMain.php';
		$restaurantTable = new restaurantTable();
		$restaurantTable->Delete($_REQUEST['SN']);
	}
		else if(isset($_REQUEST['rSN'])
		&&isset($_REQUEST['noTable'])){
		include 'classMain.php';
		$restaurantTable = new restaurantTable();
		$restaurantTable->Update($_REQUEST['rSN'],$_REQUEST['noTable']);
	}
		else{
		include 'classMain.php';
		$restaurantTable = new restaurantTable();
		$restaurantTable->Select();
	}

?>