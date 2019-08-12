<?php 
if(isset($_REQUEST['galleryName'])){
		
		include 'classMain.php';
		
		$galleryInfo = new galleryInfo();
		$galleryInfo->Insert($_REQUEST['galleryName']);
	} 
	else if(isset($_REQUEST['galleryID'])){
		include 'classMain.php';
		$galleryInfo = new galleryInfo();
		$galleryInfo->Delete($_REQUEST['galleryID']);
	}
	else if(isset($_REQUEST['idGallery'])
		&&isset($_REQUEST['nameGallery'])){
		include 'classMain.php';
		$galleryInfo = new galleryInfo();
		$galleryInfo->Update($_REQUEST['idGallery'],$_REQUEST['nameGallery']);
	}
	else if(isset($_REQUEST['gID'])){
		include 'classMain.php';
		$galleryInfo = new galleryInfo();
		$galleryInfo->GetGalleryFromID($_REQUEST['gID']);
	}
	else{
		include 'classMain.php';
		$galleryInfo = new galleryInfo();
		$galleryInfo->Select();
	}

?>