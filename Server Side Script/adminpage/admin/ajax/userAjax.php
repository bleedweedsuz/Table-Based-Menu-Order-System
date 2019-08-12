<?php
	include_once("../../classMain.php");
	
	if(isset($_GET['uName']) && isset($_GET['uPassword'])
		&& isset($_GET['uType']) && isset($_GET['uFName'])
		&& isset($_GET['uAddress']) && isset($_GET['uPhone']) 
		&& isset($_GET['uID'])){
			$uID = $_GET['uID'];
			$uName = $_GET['uName'];
			$uPassword = $_GET['uPassword'];
			$uType = $_GET['uType'];
			$uFName = $_GET['uFName'];
			$uAddress = $_GET['uAddress'];
			$uPhone = $_GET['uPhone'];
			$userDetail= new userDetail();
			$userDetail->Update($uID,$uName,$uPassword,$uType,$uFName,$uAddress,$uPhone);
	}
	elseif(isset($_GET['uName']) && isset($_GET['uPassword'])
		&& isset($_GET['uType']) && isset($_GET['uFName'])
		&& isset($_GET['uAddress']) && isset($_GET['uPhone'])){
			$uName = $_GET['uName'];
			$uPassword = $_GET['uPassword'];
			$uType = $_GET['uType'];
			$uFName = $_GET['uFName'];
			$uAddress = $_GET['uAddress'];
			$uPhone = $_GET['uPhone'];
			$userDetail= new userDetail();
			$userDetail->Insert($uName,$uPassword,$uType,$uFName,$uAddress,$uPhone);
	}
	else if(isset($_GET['uList'])){
		$userDetail= new userDetail();
		$userDetail->MakeList();
	}
	else if(isset($_GET['uDelete'])){
		$userDetail= new userDetail();
		$userDetail->Delete($_GET['uDelete']);
	}
	else{
		echo "Nothing return";
	}
	
?>