<?php
	include_once("../../classMain.php");
	if(isset($_GET['cID']) && isset($_GET['cName']) && isset($_GET['pID'])){
		$cID = $_GET['cID'];
		$cName = $_GET['cName'];
		$pID = $_GET['pID'];
		$itemCategory= new itemCategory();
		$itemCategory->Update($cID,$cName,$pID);
	}
	else if(isset($_GET['cName']) && isset($_GET['pID'])){
		$cName = $_GET['cName'];
		$pID = $_GET['pID'];
		$itemCategory= new itemCategory();
		$itemCategory->Insert($cName,$pID);
	}
	else if(isset($_GET['iList'])){
		$itemCategory= new itemCategory();
		$itemCategory->SelectItemCategory();
	}
	else if(isset($_GET['iListOption'])){
		$itemCategory= new itemCategory();
		$itemCategory->SelectAsOptions();
	}
	else if(isset($_GET['iDelete'])){
		$Cid = $_GET['iDelete'];
		$itemCategory= new itemCategory();
		$itemCategory->Delete($Cid);
	}
	else{
		echo "Nothing return";
	}
	
?>