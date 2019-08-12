<?php 
if(isset($_REQUEST['image']) 
		&& isset($_REQUEST['galleryID'])){
		
		include 'classMain.php';
		
		$photo = new photo();
		$photo->Insert($_REQUEST['image'],$_REQUEST['galleryID']);
	} 
	else if(isset($_REQUEST['pID'])){
		include 'classMain.php';
		$photo = new photo();
		$photo->Delete($_REQUEST['pID']);
	}
		else if(isset($_REQUEST['idP'])
		&&isset($_REQUEST['Eimage'])
		&& isset($_REQUEST['idGallery'])){
		include 'classMain.php';
		$photo = new photo();
		$photo->Update($_REQUEST['idP'],$_REQUEST['Eimage'],$_REQUEST['idGallery']);
	}
		else{
		include 'classMain.php';
		$photo = new photo();
		$photo->Select();
	}

?>