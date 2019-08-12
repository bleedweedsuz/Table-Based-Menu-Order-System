<?php
	if(isset($_REQUEST['userName']) 
		&& isset($_REQUEST['userPassword']) 
		&& isset($_REQUEST['userType'])
		&& isset($_REQUEST['uName'])
		&& isset($_REQUEST['uAddress'])
		&& isset($_REQUEST['uPhone'])){
		
		include 'classMain.php';
		
		$userDetail = new userDetail();
		$userDetail->Insert($_REQUEST['userName'],$_REQUEST['userPassword'],$_REQUEST['userType'],$_REQUEST['uName'],$_REQUEST['uAddress'],$_REQUEST['uPhone']);
	} 
	else if(isset($_REQUEST['uID'])){
		include 'classMain.php';
		$userDetail = new userDetail();
		$userDetail->Delete($_REQUEST['uID']);
	}
	else if(isset($_REQUEST['idU'])
		&&isset($_REQUEST['nameUser'])
		&& isset($_REQUEST['passwordUser'])
		&& isset($_REQUEST['typeUser']) 
		&& isset($_REQUEST['nameU']) 
		&& isset($_REQUEST['addressU']) 
		&& isset($_REQUEST['phoneU'])){
		include 'classMain.php';
		$userDetail = new userDetail();
		$userDetail->Update($_REQUEST['idU'],$_REQUEST['nameUser'],$_REQUEST['passwordUser'],$_REQUEST['typeUser'],$_REQUEST['nameU'],$_REQUEST['addressU'],$_REQUEST['phoneU']);
	}
	else if(isset($_REQUEST['userName']) && isset($_REQUEST['userPassword'])){
		include 'classMain.php';
		$userDetail = new userDetail();
		$userDetail->SelectUser($_REQUEST['userName'],$_REQUEST['userPassword']);
	}
	else{
		include 'classMain.php';
		$userDetail = new userDetail();
		$userDetail->Select();
	}

?>