<?php 
	if(isset($_REQUEST['fName']) 
		&& isset($_REQUEST['fPrice'])
		&& isset($_REQUEST['fDescription'])
		&& isset($_REQUEST['galleryID'])
		&& isset($_REQUEST['CID'])){
		
		include 'classMain.php';
		
		$foodItem = new foodItem();
		$foodItem->Insert($_REQUEST['fName'],$_REQUEST['fPrice'],$_REQUEST['fDescription'],$_REQUEST['galleryID'],$_REQUEST['CID']);
	} 
	else if(isset($_REQUEST['foodID'])){
		include 'classMain.php';
		$foodItem = new foodItem();
		$foodItem->Delete($_REQUEST['foodID']);
	}
		else if(isset($_REQUEST['idFood'])
		&&isset($_REQUEST['nameF'])
		&& isset($_REQUEST['priceF'])
		&& isset($_REQUEST['descriptionF'])
		&& isset($_REQUEST['idGallery']) 
		&& isset($_REQUEST['idC'])){
		include 'classMain.php';
		$foodItem = new foodItem();
		$foodItem->Update($_REQUEST['idFood'],$_REQUEST['nameF'],$_REQUEST['priceF'],$_REQUEST['descriptionF'],$_REQUEST['idGallery'],$_REQUEST['idC']);
	}
	else if(isset($_REQUEST['SCID'])){
		include 'classMain.php';
		$foodItem = new foodItem();
		$foodItem->Select($_REQUEST['SCID']);
	}




?>