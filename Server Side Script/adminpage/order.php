<?php 
	if(isset($_REQUEST['jString'])){
		include 'classMain.php';	
	        $jString = $_REQUEST['jString'];
	        $jString= str_replace("\\","",$jString);
		$jDecode = json_decode($jString,true);
		foreach ($jDecode as $k)    
		{
			$order = new order();
			$order->Insert($k["TableNo"],$k["FoodID"],1,$k["Note"],"--",1,$k["Token"],$k["Price"]);
		} 
	} 
	else if(isset($_REQUEST['SN'])){
		include 'classMain.php';
		$order = new order();
		$order->Delete($_REQUEST['SN']);
	}
	else if(isset($_REQUEST['oSN'])
	&&isset($_REQUEST['noTable'])
	&& isset($_REQUEST['idFood'])
	&& isset($_REQUEST['oQuantity'])
	&& isset($_REQUEST['oNote']) 
	&& isset($_REQUEST['oTime'])
	&& isset($_REQUEST['oState'])){
		include 'classMain.php';
		$order = new order();
		$order->Update($_REQUEST['oSN'],$_REQUEST['noTable'],$_REQUEST['idFood'],$_REQUEST['oQuantity'],$_REQUEST['oNote'],$_REQUEST['oTime'],$_REQUEST['oState']);
	}
	else if(isset($_REQUEST['TOKEN'])){
		include 'classMain.php';
		$TOKEN=$_REQUEST['TOKEN'];
		$order = new order();
		$order->SelectPriceDetails($TOKEN);
	}
	else if(isset($_REQUEST['LTOKEN'])){
		include 'classMain.php';
		$TOKEN=$_REQUEST['LTOKEN'];
		$order = new order();
		$order->Leave($TOKEN);
	}
	else{
		include 'classMain.php';
		$order = new order();
		$order->Select();
	}
?>