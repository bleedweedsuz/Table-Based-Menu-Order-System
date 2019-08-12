<?php
	include_once("../../classMain.php");
	if(isset($_GET['gID']) && isset($_GET['gName'])){
		$gID = $_GET['gID'];
		$galleryName = $_GET['gName'];
		$galleryInfo = new galleryInfo();
		$galleryInfo->Update($gID,$galleryName);
	}
	else if(isset($_GET['gName'])){
		$galleryName = $_GET['gName'];
		$galleryInfo = new galleryInfo();
		$galleryInfo->Insert($galleryName);
	}
	else if(isset($_GET['gList'])){
		$galleryInfo = new galleryInfo();
		$galleryInfo->MakeList();
	}
	else if(isset($_GET['gDelete'])){
		$gID = $_GET['gDelete'];
		$galleryInfo = new galleryInfo();
		$galleryInfo->Delete($gID);
	}
	else if(isset($_GET['gIDSelected'])){
		$gID = $_GET['gIDSelected'];
		$photo = new photo();
		$photo->SelectByGid($gID);
	}
	else if(isset($_GET['pIDSelected'])){
		$pID = $_GET['pIDSelected'];
		$photo = new photo();
		$photo->Delete($pID);
	}
	else if(isset($_GET['gList2'])){
		$gBox = $_GET['gList2']; 
		$galleryInfo = new galleryInfo();
		$galleryInfo->MakeListChooseIcon("IconID",$gBox);
	}
	else if(isset($_GET['gList3'])){
		$gBox = $_GET['gList3']; 
		$galleryInfo = new galleryInfo();
		$galleryInfo->MakeListChooseIcon2("galleryID",$gBox);
	}
	else{
		echo "Nothing return";
	}
	
?>