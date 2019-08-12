<?php
	if(isset($_REQUEST['TOKEN'])){
		include 'classMain.php';
		$TokenGenerator = new TokenGenerator();
		$TokenGenerator->GenerateToken();
	}
	else if(isset($_REQUEST['TNO'])){
		include 'classMain.php';
		$TNO = $_REQUEST['TNO'];
		$TokenGenerator = new TokenGenerator();
		$TokenGenerator->CheckIfPaidOrNot($TNO);
	}
?>