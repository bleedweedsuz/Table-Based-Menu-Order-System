<?php
	include_once("../../classMain.php");
	if(isset($_GET['fID']) && isset($_GET['fName']) && isset($_GET['fPrice']) 
		&& isset($_GET['fDescription']) && isset($_GET['galleryID'])
		&& isset($_GET['CID'])){
		
		$fID = $_GET['fID'];
		$fName = $_GET['fName'];
		$fPrice = $_GET['fPrice'];
		$fDescription = $_GET['fDescription'];
		$galleryID = $_GET['galleryID'];
		$CID = $_GET['CID'];
		$foodItem= new foodItem();
		$foodItem->Update($fID,$fName,$fPrice,$fDescription,$galleryID,$CID);
	}
	else if(isset($_GET['fName']) && isset($_GET['fPrice']) 
		&& isset($_GET['fDescription']) && isset($_GET['galleryID'])
		&& isset($_GET['CID'])){
		$fName = $_GET['fName'];
		$fPrice = $_GET['fPrice'];
		$fDescription = $_GET['fDescription'];
		$galleryID = $_GET['galleryID'];
		$CID = $_GET['CID'];
		$foodItem= new foodItem();
		$foodItem->Insert($fName,$fPrice,$fDescription,$galleryID,$CID);
	}
	else if(isset($_GET['fList'])){
		$foodItem= new foodItem();
		$foodItem->SelectFoodItemsList();
	}
	else if(isset($_GET['fDelete'])){
		$fID = $_GET['fDelete'];
		$foodItem= new foodItem();
		$foodItem->Delete($fID);
	}
	else{
		echo "Nothing return";
	}
	
?>