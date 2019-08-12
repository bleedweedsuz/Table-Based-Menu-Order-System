<?php
	if(isset($_GET['gID'])){
		include_once "../classMain.php";
		$uploadImage = new uploadImage($_GET['gID']);
		$uploadImage->upload();
	}
?>