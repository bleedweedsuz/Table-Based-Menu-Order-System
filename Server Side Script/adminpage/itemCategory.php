<?php 
if(isset($_REQUEST['cName']) 
		&& isset($_REQUEST['pID'])){
		
		include 'classMain.php';
		
		$itemCategory = new itemCategory();
		$itemCategory->Insert($_REQUEST['cName'],$_REQUEST['pID']);
	} 
	else if(isset($_REQUEST['CID'])){
		include 'classMain.php';
		$itemCategory = new itemCategory();
		$itemCategory->Delete($_REQUEST['CID']);
	}
		else if(isset($_REQUEST['idC'])
		&&isset($_REQUEST['nameC'])
		&& isset($_REQUEST['idP'])){
		include 'classMain.php';
		$itemCategory = new itemCategory();
		$itemCategory->Update($_REQUEST['idC'],$_REQUEST['nameC'],$_REQUEST['idP']);
	}
		else{
		include 'classMain.php';
		$itemCategory = new itemCategory();
		$itemCategory->Select();
	}

?>